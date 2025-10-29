DESCRIPTION = "Variscite startup scripts for use with Variscite wireless modules"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6"

SRC_URI = " \
    file://variscite-wifi \
    file://variscite-wifi.service \
    file://variscite-bt \
    file://variscite-bt.service \
    file://variscite-ot \
    file://variscite-ot-client \
    file://variscite-ot-server \
    file://variscite-ot.service \
    file://variscite-wireless \
"

S = "${WORKDIR}/sources"
UNPACKDIR = "${S}"

inherit systemd

PACKAGES =+ " \
    ${PN}-bt \
    ${PN}-ot \
    ${PN}-wifi \
"

SYSTEMD_SERVICE:${PN}-bt = " \
    variscite-bt.service \
"
SYSTEMD_AUTO_ENABLE:${PN}-bt = "enable"

SYSTEMD_SERVICE:${PN}-ot = " \
    variscite-ot.service \
"
SYSTEMD_AUTO_ENABLE:${PN}-ot = "enable"

SYSTEMD_SERVICE:${PN}-wifi = " \
    variscite-wifi.service \
"
SYSTEMD_AUTO_ENABLE:${PN}-wifi = "enable"

SYSTEMD_PACKAGES = "\
    ${PN}-bt \
    ${PN}-ot \
    ${PN}-wifi \
"

do_install() {
    install -Dm 0755 ${S}/variscite-wifi ${D}/${sysconfdir}/wifi/variscite-wifi
    install -Dm 0644 ${S}/variscite-wireless ${D}/${sysconfdir}/wifi/variscite-wireless
    install -Dm 0644 ${S}/variscite-wifi.service ${D}/${systemd_unitdir}/system/variscite-wifi.service

    install -Dm 0755 ${S}/variscite-bt ${D}/${sysconfdir}/bluetooth/variscite-bt
    install -Dm 0644 ${S}/variscite-bt.service ${D}/${systemd_unitdir}/system/variscite-bt.service

    install -Dm 0755 ${S}/variscite-ot ${D}/${sysconfdir}/openthread/variscite-ot
    install -Dm 0755 ${S}/variscite-ot-server ${D}/${sysconfdir}/openthread/variscite-ot-server
    install -Dm 0755 ${S}/variscite-ot-client ${D}/${sysconfdir}/openthread/variscite-ot-client
    install -Dm 0644 ${S}/variscite-ot.service ${D}/${systemd_unitdir}/system/variscite-ot.service
}

RDEPENDS:${PN}-ot = "${PN}-bt"

FILES:${PN}-bt = " \
    ${sysconfdir}/bluetooth/* \
    ${systemd_unitdir}/system \
"

FILES:${PN}-ot = " \
    ${sysconfdir}/openthread/* \
    ${systemd_unitdir}/system \
"

FILES:${PN}-wifi = " \
    ${sysconfdir}/wifi/* \
    ${systemd_unitdir}/system \
"
