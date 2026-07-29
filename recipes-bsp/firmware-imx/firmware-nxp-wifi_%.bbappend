# Use Variscite WiFi module parameters

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = " \
	file://var_wifi_mod_para.conf \
"

do_install:append() {
    install -m 0755 ${UNPACKDIR}/var_wifi_mod_para.conf ${D}${nonarch_base_libdir}/firmware/nxp/wifi_mod_para.conf
}
