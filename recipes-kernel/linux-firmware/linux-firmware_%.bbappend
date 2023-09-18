# Support additional firmware for wl18xx WIFI+BT modules

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRCREV_FORMAT = "linux-firmware"

# TI WiFi FW 8.9.0.0.88 and BT FW 4.7
SRCREV_tiwlan = "d5c271f60bb2d90b7c2b03ce792d01af2f0a8e44"
BRANCH_tiwlan = "master"
SRCREV_tibt = "3aa1d75f3c2ae77f6e4d36194e3d281b899ab149"
BRANCH_tibt = "master"

SRC_URI:append = " \
	git://git.ti.com/cgit/wilink8-wlan/wl18xx_fw;protocol=https;branch=${BRANCH_tiwlan};destsuffix=tiwlan;name=tiwlan \
	git://git.ti.com/cgit/ti-bt/service-packs;protocol=https;branch=${BRANCH_tibt};destsuffix=tibt;name=tibt \
	file://wl1271-nvs.bin \
"
do_install:append() {
	install -m 0755 ${WORKDIR}/tibt/initscripts/TIInit_*.bts ${D}${nonarch_base_libdir}/firmware/ti-connectivity
	install -m 0755 ${WORKDIR}/tiwlan/*.bin ${D}${nonarch_base_libdir}/firmware/ti-connectivity
	install -m 0755 ${WORKDIR}/wl1271-nvs.bin ${D}${nonarch_base_libdir}/firmware/ti-connectivity
}
