/*
 * Copyright (C) 2022 Dremio
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.projectnessie.gc.files;

import java.net.URI;
import org.immutables.value.Value;

/** References a file using a {@link #base()} URI plus a relative {@link #path()}. */
@Value.Immutable
public interface FileReference {

  /** URI to the file/directory relative to {@link #base()}. */
  @Value.Parameter(order = 1)
  URI path();

  /** Base location as from for example Iceberg's table-metadata. */
  @Value.Parameter(order = 2)
  URI base();

  /** The file's last modification timestamp, if available, or {@code -1L} if not available. */
  @Value.Parameter(order = 3)
  @Value.Auxiliary
  long modificationTimeMillisEpoch();

  @Value.NonAttribute
  default URI absolutePath() {
    return base().resolve(path());
  }

  static ImmutableFileReference.Builder builder() {
    return ImmutableFileReference.builder();
  }

  static FileReference of(URI path, URI base, long modificationTimeMillisEpoch) {
    return ImmutableFileReference.of(path, base, modificationTimeMillisEpoch);
  }
}
