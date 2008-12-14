Readme

Ant-Build for creating an Eclipse update site for LWJGL
Author: Jens von Pilgrim <developer@jevopi.de>


Required software:
------------------
 - Eclipse 3.x
 - ant 1.7
 
Note: This build file is for LWJGL version 2.x, the optional parts
shipped with 1.x are no longer supported.

The following 3rd party libraries are included:

- ant4eclipse
  URL: http://ant4eclipse.sourceforge.net/
  License: Sun Public License
  Ant taks to use Eclipse features with Ant
  
- packagelist 
  Author: Jens von Pilgrim
  Ant task extracting package names (or generally directory names) into given
  property. Source code included.  

- ecj.jar (from JDT) and org.eclipse.osgi_3.2.0.v20060601.jar
  License: EPL
  Eclipse libraries required for ant4eclipse

Steps:
------

1) Specify your Eclipse installation directory in build.xml, line 19:
	
	<property name="eclipse.install" value="c:/Devel/Eclipse3.3" />

2) Specify your Eclipse update site url in build.xml, line 20 and
	make other adjustent in build.xml if necessary.


2) Download the lwjgl version you want to build an update site for and
	store all zip files in directory "lwjgl-archives" in org.lwjgl.eclipseplugins using the
	version as a directory, e.g.

	lwjgl-archives
		+ 2.0.0
			- lwjgl-2.0.0.zip
			- lwjgl-docs-2.0.0.zip
			- lwjgl-source-2.0.0.zip
			- lwjgl_applet-2.0.0.zip
			
	Note that the version number must match the following pattern [0-9]+\.[0-9]+\.[0-9]+, i.e. three point separated numbers.
	So you must to rename these archives accordingly, and you maybe must also rename the of the folder in the applet archive. 		
			
3) Run build file and specify the version as a command line parameters, e.g.

	ant -Dversion=2.0.0 dist

4) You will find the complete update site ready in

	build/plugins/org.lwjgl.updatesite

	You have to upload the complete folder to the url you specified in step 2.
	That's it :-)





