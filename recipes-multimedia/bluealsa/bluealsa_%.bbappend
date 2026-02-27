#fix QA error when building static libraries
FILES:${PN}-staticdev += "${libdir}/alsa-lib/*.a"

SYSTEMD_BLUEALSA_ARGS:append = " -p hfp-hf -p hfp-ag -p hsp-hs -p hsp-ag"
