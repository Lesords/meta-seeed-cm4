#!/bin/bash

git clone -b kirkstone https://github.com/openembedded/meta-openembedded.git --depth=1
git clone -b kirkstone git://git.yoctoproject.org/meta-raspberrypi --depth=1

source oe-init-build-env # in build dir

bitbake-layers add-layer ../meta-raspberrypi
bitbake-layers add-layer ../meta-openembedded/meta-oe/
bitbake-layers add-layer ../meta-openembedded/meta-python/

# modify local.conf to build raspberrypi5 system
sed -i '/^MACHINE/s/= .*$/= "raspberrypi5"/g' conf/local.conf
echo "ENABLE_UART = \"1\"" >> conf/local.conf

# building image
bitbake rpi-test-image
