SUMMARY = "ROS Flutter Demo Application"
DESCRIPTION = "A reference ROS2 Flutter Project using rcldart library"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"
SECTION = "graphics"

SRC_URI = "git://github.com/danascape/flutter-ros-demo.git;branch=master;protocol=https \
           file://agl-app-flutter@ros-flutter.service \
           "

SRCREV = "0b7d39fe544c385b0967dc9cb5730e3e54f35df7"
S = "${WORKDIR}/git"

inherit flutter-app

# flutter-app
PUBSPEC_APPNAME = "ros_flutter"
PUBSPEC_IGNORE_LOCKFILE = "1"
FLUTTER_BUILD_ARGS = "bundle -v"

# Python runtime dependencies for scripts
RDEPENDS:${PN} += " \
    python3-core \
    python3-numpy \
    python3-opencv \
    python3-threading \
    python3-json \
    python3-io \
    python3-logging \
    python3-fer \
    python3-dlib \
"

do_install:append() {
    install -D -m 0644 ${WORKDIR}/agl-app-flutter@ros-flutter.service ${D}${systemd_system_unitdir}/agl-app-flutter@ros-flutter.service

    install -d ${D}${bindir}
    install -m 0755 ${S}/scripts/detection_mode_controller.py ${D}${bindir}/
    install -m 0755 ${S}/scripts/driver_mood_detection.py ${D}${bindir}/
    install -m 0755 ${S}/scripts/enhanced_road_safety_camera.py ${D}${bindir}/
    install -m 0755 ${S}/scripts/launch_detection_system.py ${D}${bindir}/
}
