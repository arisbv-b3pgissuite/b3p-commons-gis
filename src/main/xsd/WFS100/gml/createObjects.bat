@echo off
REM Change the following line to set your JDK path
set JDK_DIR=%JAVA_HOME%

@echo Create the classpath
set CP=;
for %%i in (..\..\..\dist\lib\*.jar) do call cp.bat %%i
set CP=%CP%;"%JDK_DIR%\lib\tools.jar"
echo %CP%

@echo Codetabellen import
@echo.
@echo Generating classes...

@rem Java 2 style collection types

@echo GML
"%JDK_DIR%\bin\java" -cp %CP% org.exolab.castor.builder.SourceGenerator -i ../v100/customGML.xsd -types j2 -package nl.b3p.xml.gml.v212 

@pause