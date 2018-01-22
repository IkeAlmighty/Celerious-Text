@echo OFF

del *.class

cd model
del *.class
cd ../

cd viewer
del *.class
cd ../

cd controller
del *.class
cd ../

@echo ON