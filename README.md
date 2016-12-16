# ACL2016_master1

## REQUIREMENTS

- [JDK8]

## TECHS

- [LibGDX] : Cross Platform. Publish your games on Windows, Mac, Linux, Android, iOS, BlackBerry and HTML5, all with the same code base.


## INSTALLATION

```
$ git clone ['repo-url']
```

To import in Eclipse: File -> Import -> General -> Existing Projects into Workspace
To import to Intellij IDEA: File -> Import -> Folder

IDE Setting : https://github.com/libgdx/libgdx/wiki/Setting-up-your-Development-Environment-%28Eclipse%2C-Intellij-IDEA%2C-NetBeans%29


## CLI

### RUN 

```
Windows : 
gradlew desktop:run
Linux : 
./gradlew desktop:run
```

### TESTS

```
Windows :
gradlew core:test
Linux : 
./gradlew core:test
````

### CREATE JAR FILE

```
gradlew desktop:dist
```
The runnable JAR file is located in `desktop/build/libs/`





[LibGDX]: <https://libgdx.badlogicgames.com>
[JDK8]: <https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html>
