#@DESCRIPTION: Variscite brcm_patchram_plus network apps"
#
# http://www.variscite.com

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI = "git://github.com/varigit/brcm-patchram-plus.git;protocol=https;branch=main"
SRCREV = "4b1f9eb988b011ec91fd65d1385e57ee7b1342ca"

do_compile() {
        oe_runmake
}

do_install() {
        install -d ${D}${bindir}/
        install -m 0755 ${B}/brcm_patchram_plus ${D}${bindir}/
}

FILES:${PN} = "${bindir}/brcm_patchram_plus"
