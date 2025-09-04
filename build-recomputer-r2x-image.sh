#!/bin/bash

git clone -b scarthgap https://github.com/Lesords/meta-seeed-cm4.git meta-seeed-reterminal --depth=1
git clone -b scarthgap https://github.com/agherzan/meta-raspberrypi.git --depth=1
git clone -b scarthgap https://github.com/openembedded/meta-openembedded.git --depth=1

source oe-init-build-env # in build dir

bitbake-layers add-layer ../meta-raspberrypi
bitbake-layers add-layer ../meta-seeed-reterminal
bitbake-layers add-layer ../meta-openembedded/meta-oe
bitbake-layers add-layer ../meta-openembedded/meta-python

# modify local.conf to build raspberrypi4 64-bit system
sed -i '/^MACHINE/s/= .*$/= "seeed-recomputer-r2x"/g' conf/local.conf
sed -i 's/#SSTATE_DIR/SSTATE_DIR/g' conf/local.conf

cat >> conf/local.conf <<EOF
INIT_MANAGER = "systemd"
LICENSE_FLAGS_ACCEPTED = "synaptics-killswitch"
INHERIT:remove = "create-spdx"
EOF

# we don't run start the compile here.
# because we need to copy the cache to build/ dir before we do that.
