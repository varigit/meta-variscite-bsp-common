FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

# Variscite patch to make reset gpio optional
SRC_URI += "\
    file://0001-ot-daemon-spi_interface-make-reset-device-and-line-o.patch \
"
# i.MX9 SPI controller does not support MOSI idle level control (SPI_MOSI_IDLE_LOW).
SRC_URI:remove:mx9-nxp-bsp = "\
    file://otpatches-082-d31bcee/0062-host-handle-power-save-mode-on-ssp-rxd.patch \
"
