/*
 * Copyright 2015-2020 Alexandr Evstigneev
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

package com.perl5.lang.pod.idea.structureView;

import com.intellij.ide.structureView.StructureViewModel;
import com.intellij.ide.structureView.StructureViewModelBase;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.smartTree.Sorter;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class PodStructureViewModel extends StructureViewModelBase implements StructureViewModel.ElementInfoProvider {
  public PodStructureViewModel(@NotNull PsiFile psiFile, @Nullable Editor editor) {
    super(psiFile, editor, new PodStructureViewElement(psiFile));
  }

  @Override
  public boolean isAlwaysShowsPlus(StructureViewTreeElement structureViewTreeElement) {
    return false;
  }

  @Override
  public boolean isAlwaysLeaf(StructureViewTreeElement structureViewTreeElement) {
    return false;
  }

  @Override
  public @NotNull Sorter[] getSorters() {
    return new Sorter[]{Sorter.ALPHA_SORTER};
  }
}
