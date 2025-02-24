// Copyright 2023 The Bazel Authors. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//

package com.google.devtools.build.lib.bazel.bzlmod;

import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.ryanharter.auto.value.gson.GenerateTypeAdapter;

/** Stores the values of flags and environment variables that affect the resolution */
@AutoValue
@GenerateTypeAdapter
abstract class BzlmodFlagsAndEnvVars {

  public static BzlmodFlagsAndEnvVars create(
      ImmutableList<String> registries,
      ImmutableMap<String, String> moduleOverrides,
      ImmutableList<String> yankedVersions,
      String envVarYankedVersions,
      boolean ignoreDevDeps,
      String directDepsMode,
      String compatabilityMode) {
    return new AutoValue_BzlmodFlagsAndEnvVars(
        registries,
        moduleOverrides,
        yankedVersions,
        envVarYankedVersions,
        ignoreDevDeps,
        directDepsMode,
        compatabilityMode);
  }

  /** Registries provided via command line */
  public abstract ImmutableList<String> getCmdRegistries();

  /** ModulesOverride provided via command line */
  public abstract ImmutableMap<String, String> getCmdModuleOverrides();

  /** Allowed yanked version in the dependency graph */
  public abstract ImmutableList<String> getAllowedYankedVersions();

  /** Allowed yanked version in the dependency graph from environment variables */
  public abstract String getEnvVarAllowedYankedVersions();

  /** Whether to ignore things declared as dev dependencies or not */
  public abstract boolean ignoreDevDependency();

  /** Error level of direct dependencies check */
  public abstract String getDirectDependenciesMode();

  /** Error level of bazel compatability check */
  public abstract String getCompatibilityMode();
}
