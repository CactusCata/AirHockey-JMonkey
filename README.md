# JavaFX Sample Maven Project
a simple sample of the use of JavaFX with Maven

## Build
Enter the following commands:

`mvn clean`

`mvn compile`

`mvn package`

## Run
The following commands are running the official tutorials

1. [Hello World](https://wiki.jmonkeyengine.org/docs/3.4/tutorials/beginner/hello_simpleapplication.html): 
`java -cp "target/jmonkey-1.0.0-SNAPSHOT-dist/*" fr.utln.jmonkey.tutorials.HelloWorld`

2. [Hello Node](https://wiki.jmonkeyengine.org/docs/3.4/tutorials/beginner/hello_node.html): 
`java -cp "target/jmonkey-1.0.0-SNAPSHOT-dist/*" fr.utln.jmonkey.tutorials.HelloNode`

2. [Hello Assets](https://wiki.jmonkeyengine.org/docs/3.4/tutorials/beginner/hello_asset.html): 
`java -cp "target/jmonkey-1.0.0-SNAPSHOT-dist/*" fr.utln.jmonkey.tutorials.HelloAssets`

If you are on MacOS and if the launch of applications fail, you have to:

- Add the VM option -XstartOnFirstThread
- When creating a JMonkey application use the following code:
```
AppSettings settings=new AppSettings(true);

MyApplication app = new MyApplication();

app.setShowSettings(false);
app.setSettings(settings);
```

## Tutorials
[https://wiki.jmonkeyengine.org/docs/3.4/tutorials/beginner/beginner.html](https://wiki.jmonkeyengine.org/docs/3.4/tutorials/beginner/beginner.html)


https://www.inventati.org/1337gallery/scripts/jme-ttf/jttf.html
https://hub.jmonkeyengine.org/t/jme-truetypefont-rendering-library/35395

Collision listener
https://wiki.jmonkeyengine.org/docs/3.4/core/gui/nifty_gui_xml_layout.html