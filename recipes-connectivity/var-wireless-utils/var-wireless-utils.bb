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

S = "${WORKDIR}"

inherit ${@bb.utils.contains('DISTRO_FEATURES','systemd','systemd','update-rc.d',d)}

PACKAGES =+ " \
    ${PN}-bt \
    ${PN}-ot \
    ${PN}-wifi \
"

INITSCRIPT_NAME:${PN}-bt = "variscite-bt"
INITSCRIPT_PARAMS:${PN}-bt = "start 99 2 3 4 5 ."

INITSCRIPT_NAME:${PN}-ot = "variscite-ot"
INITSCRIPT_PARAMS:${PN}-ot = "start 100 2 3 4 5 ."

INITSCRIPT_NAME:${PN}-wifi = "variscite-wifi"
INITSCRIPT_PARAMS:${PN}-wifi = "start 5 S ."

INITSCRIPT_PACKAGES = " \
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
    install -Dm 0644 ${WORKDIR}/variscite-wireless ${D}/${sysconfdir}/wifi/variscite-wireless
    install -Dm 0755 ${WORKDIR}/variscite-ot-server ${D}/${sysconfdir}/openthread/variscite-ot-server
    install -Dm 0755 ${WORKDIR}/variscite-ot-client ${D}/${sysconfdir}/openthread/variscite-ot-client

    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -Dm 0755 ${WORKDIR}/variscite-wifi ${D}/${sysconfdir}/wifi/variscite-wifi
        install -Dm 0644 ${WORKDIR}/variscite-wifi.service ${D}/${systemd_unitdir}/system/variscite-wifi.service

        install -Dm 0755 ${WORKDIR}/variscite-bt ${D}/${sysconfdir}/bluetooth/variscite-bt
        install -Dm 0644 ${WORKDIR}/variscite-bt.service ${D}/${systemd_unitdir}/system/variscite-bt.service

        install -Dm 0755 ${WORKDIR}/variscite-ot ${D}/${sysconfdir}/openthread/variscite-ot
        install -Dm 0644 ${WORKDIR}/variscite-ot.service ${D}/${systemd_unitdir}/system/variscite-ot.service
    else
        install -Dm 0755 ${WORKDIR}/variscite-bt   ${D}${sysconfdir}/init.d/variscite-bt
        install -Dm 0755 ${WORKDIR}/variscite-ot   ${D}${sysconfdir}/init.d/variscite-ot
        install -Dm 0755 ${WORKDIR}/variscite-wifi ${D}${sysconfdir}/init.d/variscite-wifi
    fi
}

RDEPENDS:${PN}-ot = "${PN}-bt"

FILES:${PN}-bt = " \
    ${sysconfdir}/bluetooth/* \
    ${@bb.utils.contains('DISTRO_FEATURES','systemd', \
    '${systemd_unitdir}/system', \
    '${sysconfdir}/init.d/',d)} \
"

FILES:${PN}-ot = " \
    ${sysconfdir}/openthread/* \
    ${@bb.utils.contains('DISTRO_FEATURES','systemd', \
    '${systemd_unitdir}/system', \
    '${sysconfdir}/init.d/',d)} \
"

FILES:${PN}-wifi = " \
    ${sysconfdir}/wifi/* \
    ${@bb.utils.contains('DISTRO_FEATURES','systemd', \
    '${systemd_unitdir}/system', \
    '${sysconfdir}/init.d/',d)} \
"
