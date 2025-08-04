DESCRIPTION = "R21 Board Detect Service"

LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-3.0-only;md5=c79ff39f19dfec6d293b95dea7b07891"

inherit systemd

SYSTEMD_AUTO_ENABLE:${PN} = "enable"
SYSTEMD_SERVICE:${PN} = "r21_board_detect.service"
SRC_URI = "file://r21_board_detect.service \
           file://r21_board_detect.sh \
           "

INSANE_SKIP:${PN} = "file-rdeps"
INSANE_SKIP:${PN} += "buildpaths"

FILES:${PN} += "${systemd_unitdir}/system/r21_board_detect.service \
                /usr/local/bin/r21_board_detect.sh \
                "

do_install:append() {
  install -d ${D}/${systemd_unitdir}/system
  install -d ${D}/usr/local/bin/
  install -m 0644 ${WORKDIR}/r21_board_detect.service ${D}/${systemd_unitdir}/system/
  install -m 0744 ${WORKDIR}/r21_board_detect.sh ${D}/usr/local/bin/
}
