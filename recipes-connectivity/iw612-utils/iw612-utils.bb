DESCRIPTION = "Startup and config files for use with IW612 wireless module"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6"

SRC_URI = " \
	file://iw612-bt \
	file://iw612-ot \
	file://iw612-wifi \
"

FILES:${PN} = " \
	${sysconfdir}/bluetooth/variscite-bt.d*  \
	${sysconfdir}/openthread/variscite-ot.d*  \
	${sysconfdir}/wifi/variscite-wifi.d*  \
"

RDEPENDS:${PN} = " \
	i2c-tools \
	base-files \
	libgpiod-tools \
	var-gpio-utils \
	var-wireless-utils \
	ot-daemon \
"

S = "${WORKDIR}"

do_install() {
	install -d ${D}${sysconfdir}/bluetooth/variscite-bt.d
	install -m 0755 ${WORKDIR}/iw612-bt ${D}/${sysconfdir}/bluetooth/variscite-bt.d

	install -d ${D}${sysconfdir}/openthread/variscite-ot.d
	install -m 0755 ${WORKDIR}/iw612-ot ${D}/${sysconfdir}/openthread/variscite-ot.d

	install -d ${D}${sysconfdir}/wifi/variscite-wifi.d
	install -m 0755 ${WORKDIR}/iw612-wifi ${D}/${sysconfdir}/wifi/variscite-wifi.d
}

COMPATIBLE_MACHINE = "(imx93-var-som)"
