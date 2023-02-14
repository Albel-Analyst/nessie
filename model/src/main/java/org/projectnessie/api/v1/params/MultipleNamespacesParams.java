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
package org.projectnessie.api.v1.params;

import java.util.Objects;
import java.util.StringJoiner;
import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import org.eclipse.microprofile.openapi.annotations.media.ExampleObject;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.immutables.builder.Builder;
import org.projectnessie.model.Namespace;
import org.projectnessie.model.Validation;

public class MultipleNamespacesParams {

  @Parameter(
      description = "name of ref to fetch",
      examples = {@ExampleObject(ref = "ref")})
  @PathParam("ref")
  @jakarta.ws.rs.PathParam("ref")
  @NotNull
  @jakarta.validation.constraints.NotNull
  @Pattern(regexp = Validation.REF_NAME_REGEX, message = Validation.REF_NAME_MESSAGE)
  @jakarta.validation.constraints.Pattern(
      regexp = Validation.REF_NAME_REGEX,
      message = Validation.REF_NAME_MESSAGE)
  private String refName;

  @Parameter(
      description = "the name of the namespace",
      examples = {
        @ExampleObject(ref = "namespaceName"),
        @ExampleObject(ref = "emptyNamespaceName")
      })
  @QueryParam("name")
  @jakarta.ws.rs.QueryParam("name")
  @Nullable
  @jakarta.annotation.Nullable
  private Namespace namespace;

  @Parameter(
      description = "a particular hash on the given ref",
      examples = {@ExampleObject(ref = "nullHash"), @ExampleObject(ref = "hash")})
  @QueryParam("hashOnRef")
  @jakarta.ws.rs.QueryParam("hashOnRef")
  @Nullable
  @jakarta.annotation.Nullable
  private String hashOnRef;

  public MultipleNamespacesParams() {}

  @Builder.Constructor
  MultipleNamespacesParams(
      @NotNull @jakarta.validation.constraints.NotNull String refName,
      @Nullable @jakarta.annotation.Nullable Namespace namespace,
      @Nullable @jakarta.annotation.Nullable String hashOnRef) {
    this.refName = refName;
    this.namespace = namespace;
    this.hashOnRef = hashOnRef;
  }

  public String getRefName() {
    return refName;
  }

  @Nullable
  @jakarta.annotation.Nullable
  public Namespace getNamespace() {
    return namespace;
  }

  @Nullable
  @jakarta.annotation.Nullable
  public String getHashOnRef() {
    return hashOnRef;
  }

  public static MultipleNamespacesParamsBuilder builder() {
    return new MultipleNamespacesParamsBuilder();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof MultipleNamespacesParams)) {
      return false;
    }
    MultipleNamespacesParams that = (MultipleNamespacesParams) o;
    return Objects.equals(refName, that.refName)
        && Objects.equals(namespace, that.namespace)
        && Objects.equals(hashOnRef, that.hashOnRef);
  }

  @Override
  public int hashCode() {
    return Objects.hash(refName, namespace, hashOnRef);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", MultipleNamespacesParams.class.getSimpleName() + "[", "]")
        .add("refName='" + refName + "'")
        .add("namespace=" + namespace)
        .add("hashOnRef='" + hashOnRef + "'")
        .toString();
  }
}
