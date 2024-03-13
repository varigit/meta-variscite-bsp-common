DESCRIPTION = "Startup and config files for use with IW612 wireless module"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6"

SRC_URI = " \
	file://iw612-bt \
	file://iw612-ot \
	file://iw612-wifi \
	file://99-iw61x-unmanaged-devices.conf \
	file://var_wifi_mod_para.conf \
"

PACKAGECONFIG ??= "networkmanager"
PACKAGECONFIG[networkmanager] = "--with-networkmanager,--without-networkmanager"

FILES:${PN} = " \
	${sysconfdir}/bluetooth/variscite-bt.d*  \
	${sysconfdir}/openthread/variscite-ot.d*  \
	${sysconfdir}/wifi/variscite-wifi.d*  \
	${sysconfdir}/NetworkManager/conf.d/99-iw61x-unmanaged-devices.conf \
	${nonarch_base_libdir}/firmware/nxp/var_wifi_mod_para.conf \
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

	if [ "${@bb.utils.contains('PACKAGECONFIG', 'networkmanager', 'yes', 'no', d)}" = "yes" ]; then
		install -d ${D}/${sysconfdir}/NetworkManager/conf.d
		install -m 0644 ${WORKDIR}/99-iw61x-unmanaged-devices.conf ${D}/${sysconfdir}/NetworkManager/conf.d
	fi

	install -d ${D}${nonarch_base_libdir}/firmware/nxp
	install -m 0755 ${WORKDIR}/var_wifi_mod_para.conf ${D}${nonarch_base_libdir}/firmware/nxp
}

COMPATIBLE_MACHINE = "(imx93-var-som|imx8mp-var-dart|imx8mm-var-dart)"
