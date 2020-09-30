## NOTE on Setting Up Angular Client App
#### How to create client with Angular
1. In project root directory. Run `ng new web --routing --scss`. This will create a sub
folder web in the directory.

2. Enter the newly created folder, and use `ng generate component` and 
`ng generate service` to start building angular app

3. Add bootstrap by doing
```
ng add @ng-bootstrap/ng-bootstrap
```     

4. To change angular port from **4200** to **8080**, modify **_angular.json_**
with 
```
        "serve": {
          "builder": "@angular-devkit/build-angular:dev-server",
          "options": {
            ...
            "port": 8080
          },
```



#### How to include angular in spring build process
###### angular.json
1. change `"outputPath":` from `dist/<project name>` to `dist/static`
2. (may not be necessary) change angular project name to spring project name

###### build.gradle
1. Add the following
```
//build angular
def webappDir = "$projectDir/web"
sourceSets {
	main {
		resources {
			srcDirs = ["$webappDir/dist", "$projectDir/src/main/resources"]
		}
	}
}

processResources {
	dependsOn "buildAngular"
}

task buildAngular(type:Exec) {
	// installAngular should be run prior to this task
	dependsOn "installAngular"
	workingDir "$webappDir"
	inputs.dir "$webappDir"
	// Add task to the standard build group
	group = BasePlugin.BUILD_GROUP
	// ng doesn't exist as a file in windows -> ng.cmd
	if (System.getProperty("os.name").toUpperCase().contains("WINDOWS")){
		commandLine "ng.cmd", "build"
	} else {
		commandLine "ng", "build"
	}
}

task installAngular(type:Exec) {
	workingDir "$webappDir"
	inputs.dir "$webappDir"
	group = BasePlugin.BUILD_GROUP
	if (System.getProperty("os.name").toUpperCase().contains("WINDOWS")){
		commandLine "npm.cmd", "install"
	} else {
		commandLine "npm", "install"
	}
}
```