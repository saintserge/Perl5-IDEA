/*
 * Copyright 2015-2021 Alexandr Evstigneev
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

package com.perl5.lang.perl.extensions.moo;

import com.perl5.lang.perl.extensions.packageprocessor.PerlExportDescriptor;
import com.perl5.lang.perl.extensions.packageprocessor.PerlPackageLoader;
import com.perl5.lang.perl.extensions.packageprocessor.PerlPackageParentsProvider;
import com.perl5.lang.perl.extensions.packageprocessor.impl.BaseStrictWarningsProvidingProcessor;
import com.perl5.lang.perl.psi.impl.PerlUseStatementElement;
import com.perl5.lang.perl.util.PerlPackageUtil;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.perl5.lang.perl.parser.moose.MooseSyntax.*;


public class MooProcessor extends BaseStrictWarningsProvidingProcessor implements PerlPackageParentsProvider,
                                                                                  PerlPackageLoader {
  @NonNls public static final String MOO_OBJECT = PerlPackageUtil.PACKAGE_MOO + PerlPackageUtil.NAMESPACE_SEPARATOR + "Object";
  protected static final List<String> LOADED_CLASSES = Collections.singletonList(MOO_OBJECT);
  protected static final List<String> PARENT_CLASSES = LOADED_CLASSES;

  static final List<PerlExportDescriptor> EXPORTS;

  static {
    List<PerlExportDescriptor> exports = new ArrayList<>();
    Arrays.asList(
      MOOSE_KEYWORD_AFTER, MOOSE_KEYWORD_AROUND, MOOSE_KEYWORD_BEFORE, MOOSE_KEYWORD_EXTENDS, MOOSE_KEYWORD_HAS, MOOSE_KEYWORD_WITH
    ).forEach(it -> exports.add(PerlExportDescriptor.create(PerlPackageUtil.PACKAGE_MOO, it)));
    EXPORTS = List.copyOf(exports);
  }

  @Override
  public @NotNull List<String> getLoadedPackageNames(PerlUseStatementElement useStatement) {
    return LOADED_CLASSES;
  }

  @Override
  public void changeParentsList(@NotNull PerlUseStatementElement useStatement, @NotNull List<String> currentList) {
    currentList.clear();
    currentList.addAll(PARENT_CLASSES);
  }

  @Override
  public boolean hasPackageFilesOptions() {
    return false;
  }

  @Override
  public @NotNull List<PerlExportDescriptor> getImports(@NotNull PerlUseStatementElement useStatement) {
    return EXPORTS;
  }
}
