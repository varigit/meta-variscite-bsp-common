FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

# Variscite patch to make reset gpio optional
SRC_URI += "\
    file://0001-ot-daemon-spi_interface-make-reset-device-and-line-o.patch \
"
