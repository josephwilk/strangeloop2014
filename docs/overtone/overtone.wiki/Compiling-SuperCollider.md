> NOTE: Please help keep this page up to date!  Feel free to update these instructions if you run into problems on a specific platform or with a certain install method.

### Only compile your own binaries if you really need to

Unless you need something particular from the bleeding edge, it's probably easiest to either use the internal server binaries which are distributed as part of Overtone or to install and use pre-compiled binaries for your platform, which are available on the SuperCollider site here:

[http://supercollider.sourceforge.net/downloads/](http://supercollider.sourceforge.net/downloads/)

### Checkout, build and install the latest Supercollider:

`git clone git://supercollider.git.sourceforge.net/gitroot/supercollider/supercollider`

`cd supercollider`

`git submodule init`

`git submodule update`

`mkdir build`

`cd build`

`cmake ..`

If you don't have QT or don't want the QT gui you can turn it off like this: 

`cmake -DSC_QT=OFF ..`

Now compile and install:

`make`

`sudo make install`

### Checkout, build and install the latest SC3-Plugins:

`cd ..`

`git clone git://sc3-plugins.git.sourceforge.net/gitroot/sc3-plugins/sc3-plugins`

`cd sc3-plugins`

`git submodule init`

`git submodule update`

`mkdir cmake_build`

`cd cmake_build`

Now you need to point cmake to your installed SuperCollider.  On Linux the standard install from source goes to /usr/local/include/SuperCollider/, but update it to wherever you installed it.

`cmake -DSC_PATH=/usr/local/include/SuperCollider/ ..`

`make`

`sudo make install`

`sudo ldconfig`

### Run supercollider

Listening on UDP port 2345:

`/usr/local/bin/scsynth -u 2345`

### Misc

If you have problem with Help.gui, maybe you should make this directory writable:

`chmod +w ~/share/SuperCollider/Help` 