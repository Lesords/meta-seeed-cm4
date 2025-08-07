#!/bin/bash

git clone -b scarthgap https://github.com/openembedded/meta-openembedded.git --depth=1
git clone -b scarthgap git://git.yoctoproject.org/meta-raspberrypi --depth=1
git clone -b scarthgap https://github.com/Lesords/meta-hailo.git  --depth=1

source oe-init-build-env # in build dir

bitbake-layers add-layer ../meta-raspberrypi/
bitbake-layers add-layer ../meta-openembedded/meta-oe/
bitbake-layers add-layer ../meta-openembedded/meta-python/
bitbake-layers add-layer ../meta-seeed-reterminal/
bitbake-layers add-layer ../meta-hailo/meta-hailo-accelerator/
bitbake-layers add-layer ../meta-hailo/meta-hailo-libhailort/
bitbake-layers add-layer ../meta-hailo/meta-hailo-tappas/

# modify local.conf to build raspberrypi5 system
sed -i '/^MACHINE/s/= .*$/= "seeed-recomputer-r2x"/g' conf/local.conf
echo "ENABLE_UART = \"1\"" >> conf/local.conf
echo "INIT_MANAGER = \"systemd\"" >> conf/local.conf
echo "LICENSE_FLAGS_ACCEPTED = \"synaptics-killswitch\"" >> conf/local.conf

# building image
bitbake rpi-test-image
