load("@rules_python//python:defs.bzl", "py_binary", "py_library", "py_test")

package(default_visibility = ["//tools:__pkg__"])

py_binary(
    name = "build_incremental_dexmanifest",
    srcs = [":build_incremental_dexmanifest.py"],
)

sh_test(
    name = "build_incremental_dexmanifest_test",
    srcs = [
        "build_incremental_dexmanifest_test.sh",
    ],
    args = ["$(location //tools/zip:zipper)"],
    data = [
        ":build_incremental_dexmanifest",
        "//tools/zip:zipper",
    ],
    tags = [
        # TODO(laszlocsomor): fix on Windows or describe why it cannot pass.
        "no_windows",
    ],
)

py_binary(
    name = "build_split_manifest",
    srcs = ["build_split_manifest.py"],
    deps = [
        "//third_party/py/abseil",
    ],
)

py_test(
    name = "build_split_manifest_test",
    srcs = ["build_split_manifest_test.py"],
    deps = [
        ":build_split_manifest",
    ],
)

py_binary(
    name = "incremental_install",
    srcs = ["incremental_install.py"],
    deps = [
        "//third_party/py/abseil",
    ],
)

py_test(
    name = "incremental_install_test",
    srcs = ["incremental_install_test.py"],
    tags = [
        # TODO(laszlocsomor): fix on Windows or describe why it cannot pass.
        "no_windows",
    ],
    deps = [
        ":incremental_install",
        "//third_party/py/mock",
    ],
)

py_binary(
    name = "strip_resources",
    srcs = ["strip_resources.py"],
    deps = ["//third_party/py/abseil"],
)

sh_test(
    name = "strip_resources_test",
    srcs = ["strip_resources_test.sh"],
    args = ["$(location //tools/zip:zipper)"],
    data = [
        ":strip_resources",
        "//tools/zip:zipper",
    ],
    tags = [
        # TODO(laszlocsomor): fix on Windows or describe why it cannot pass.
        "no_windows",
    ],
)

py_binary(
    name = "aar_native_libs_zip_creator",
    srcs = [
        "aar_native_libs_zip_creator.py",
    ],
    deps = [
        ":junction_lib",
        "//third_party/py/abseil",
    ],
)

py_test(
    name = "aar_native_libs_zip_creator_test",
    srcs = [
        "aar_native_libs_zip_creator_test.py",
    ],
    deps = [
        ":aar_native_libs_zip_creator",
    ],
)

py_binary(
    name = "stubify_manifest",
    srcs = ["stubify_manifest.py"],
    deps = [
        "//third_party/py/abseil",
    ],
)

py_test(
    name = "stubify_manifest_test",
    srcs = ["stubify_manifest_test.py"],
    deps = [
        ":stubify_manifest",
    ],
)

py_binary(
    name = "aar_embedded_proguard_extractor",
    srcs = ["aar_embedded_proguard_extractor.py"],
    deps = [
        ":junction_lib",
        "//third_party/py/abseil",
    ],
)

py_test(
    name = "aar_embedded_proguard_extractor_test",
    srcs = ["aar_embedded_proguard_extractor_test.py"],
    deps = [":aar_embedded_proguard_extractor"],
)

py_binary(
    name = "aar_embedded_jars_extractor",
    srcs = ["aar_embedded_jars_extractor.py"],
    deps = [
        ":junction_lib",
        "//third_party/py/abseil",
    ],
)

py_test(
    name = "aar_embedded_jars_extractor_test",
    srcs = ["aar_embedded_jars_extractor_test.py"],
    deps = [":aar_embedded_jars_extractor"],
)

py_binary(
    name = "aar_resources_extractor",
    srcs = ["aar_resources_extractor.py"],
    deps = [
        ":junction_lib",
        "//third_party/py/abseil",
    ],
)

py_test(
    name = "aar_resources_extractor_test",
    srcs = ["aar_resources_extractor_test.py"],
    deps = [":aar_resources_extractor"],
)

py_binary(
    name = "resource_extractor",
    srcs = ["resource_extractor.py"],
)

py_test(
    name = "resource_extractor_test",
    srcs = ["resource_extractor_test.py"],
    deps = [":resource_extractor"],
)

py_binary(
    name = "instrumentation_test_check",
    srcs = ["instrumentation_test_check.py"],
    deps = ["//third_party/py/abseil"],
)

py_test(
    name = "instrumentation_test_check_test",
    srcs = ["instrumentation_test_check_test.py"],
    deps = [":instrumentation_test_check"],
)

py_library(
    name = "junction_lib",
    srcs = ["junction.py"],
    visibility = ["//visibility:private"],
)

py_test(
    name = "junction_test",
    srcs = select({
        "//src/conditions:windows": ["junction_test.py"],
        "//conditions:default": ["dummy_test.py"],
    }),
    main = select({
        "//src/conditions:windows": "junction_test.py",
        "//conditions:default": "dummy_test.py",
    }),
    deps = [
        ":junction_lib",
        "//src/test/py/bazel:test_base",
    ],
)

filegroup(
    name = "srcs",
    srcs = glob(
        ["**"],
        exclude = [
            "*~",
            ".*",
        ],
    ) + [
        "//tools/android/emulator:srcs",
        "//tools/android/runtime_deps:srcs",
    ],
)

filegroup(
    name = "embedded_tools",
    srcs = glob(
        ["*"],
        exclude = [
            "*_test.py",
            "*_test.sh",
            "*~",
            ".*",
        ],
    ) + [
        "//tools/android/emulator:embedded_tools",
    ],
)

# Exported for AndroidSdkRepositoryTest to use it instead of mocking it out.
exports_files(["android_sdk_repository_template.bzl"])
