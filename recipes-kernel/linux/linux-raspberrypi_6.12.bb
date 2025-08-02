LINUX_VERSION ?= "6.12.25"
LINUX_RPI_BRANCH ?= "rpi-6.12.y"
LINUX_RPI_KMETA_BRANCH ?= "yocto-6.12"

SRCREV_machine = "3dd2c2c507c271d411fab2e82a2b3b7e0b6d3f16"
SRCREV_meta = "1f6ab68a1d86836bf1b82b791df03da3cfeacb3f"

KMETA = "kernel-meta"

SRC_URI = " \
    git://github.com/raspberrypi/linux.git;name=machine;branch=${LINUX_RPI_BRANCH};protocol=https \
    git://git.yoctoproject.org/yocto-kernel-cache;type=kmeta;name=meta;branch=${LINUX_RPI_KMETA_BRANCH};destsuffix=${KMETA} \
    file://powersave.cfg \
    file://android-drivers.cfg \
    "

require linux-raspberrypi.inc

KERNEL_DTC_FLAGS += "-@ -H epapr"

RDEPENDS:${KERNEL_PACKAGE_NAME}:raspberrypi-armv7:append = " ${RASPBERRYPI_v7_KERNEL_PACKAGE_NAME}"
RDEPENDS:${KERNEL_PACKAGE_NAME}-base:raspberrypi-armv7:append = " ${RASPBERRYPI_v7_KERNEL_PACKAGE_NAME}-base"
RDEPENDS:${KERNEL_PACKAGE_NAME}-image:raspberrypi-armv7:append = " ${RASPBERRYPI_v7_KERNEL_PACKAGE_NAME}-image"
RDEPENDS:${KERNEL_PACKAGE_NAME}-dev:raspberrypi-armv7:append = " ${RASPBERRYPI_v7_KERNEL_PACKAGE_NAME}-dev"
RDEPENDS:${KERNEL_PACKAGE_NAME}-vmlinux:raspberrypi-armv7:append = " ${RASPBERRYPI_v7_KERNEL_PACKAGE_NAME}-vmlinux"
RDEPENDS:${KERNEL_PACKAGE_NAME}-modules:raspberrypi-armv7:append = " ${RASPBERRYPI_v7_KERNEL_PACKAGE_NAME}-modules"
RDEPENDS:${KERNEL_PACKAGE_NAME}-dbg:raspberrypi-armv7:append = " ${RASPBERRYPI_v7_KERNEL_PACKAGE_NAME}-dbg"

DEPLOYDEP = ""
DEPLOYDEP:raspberrypi-armv7 = "${RASPBERRYPI_v7_KERNEL}:do_deploy"
do_deploy[depends] += "${DEPLOYDEP}"

do_configure[network] = "1"
do_configure:append(){
        if [ -d ${WORKDIR}/seeed/ ]; then
                rm -r ${WORKDIR}/seeed/
        fi
        mkdir -p ${WORKDIR}/seeed/
        if ${@bb.utils.contains('MACHINE', 'seeed-recomputer-r2x', 'true', 'false', d)}; then
            wget -P ${WORKDIR}/seeed/ \
                https://raw.githubusercontent.com/Seeed-Studio/seeed-linux-dtoverlays/master/overlays/rpi/reComputer-R2x-overlay.dts
            cp ${WORKDIR}/seeed/reComputer-R2x-overlay.dts ${S}/arch/arm/boot/dts/overlays/

            wget -P ${WORKDIR}/seeed/ \
                https://raw.githubusercontent.com/Seeed-Studio/seeed-linux-dtoverlays/master/overlays/rpi/reComputer-R21-overlay.dts
            cp ${WORKDIR}/seeed/reComputer-R21-overlay.dts ${S}/arch/arm/boot/dts/overlays/
        else
            bbdebug 1 "No target device tree specified, check your MACHINE config"
        fi
        if [ -d ${WORKDIR}/seeed/ ]; then
                rm -r ${WORKDIR}/seeed/
        fi
}
