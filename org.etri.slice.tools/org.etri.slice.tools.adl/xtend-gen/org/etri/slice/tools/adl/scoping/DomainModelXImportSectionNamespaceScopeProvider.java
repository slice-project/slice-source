package org.etri.slice.tools.adl.scoping;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.scoping.impl.ImportNormalizer;
import org.eclipse.xtext.xbase.scoping.XImportSectionNamespaceScopeProvider;

@SuppressWarnings("all")
public class DomainModelXImportSectionNamespaceScopeProvider extends XImportSectionNamespaceScopeProvider {
  private static List<QualifiedName> s_imports = new ArrayList<QualifiedName>();
  
  public static boolean addImplicitImport(final QualifiedName qname) {
    boolean _xblockexpression = false;
    {
      boolean _contains = DomainModelXImportSectionNamespaceScopeProvider.s_imports.contains(qname.append("context"));
      boolean _not = (!_contains);
      if (_not) {
        DomainModelXImportSectionNamespaceScopeProvider.s_imports.add(qname.append("context"));
      }
      boolean _contains_1 = DomainModelXImportSectionNamespaceScopeProvider.s_imports.contains(qname.append("event"));
      boolean _not_1 = (!_contains_1);
      if (_not_1) {
        DomainModelXImportSectionNamespaceScopeProvider.s_imports.add(qname.append("event"));
      }
      boolean _xifexpression = false;
      boolean _contains_2 = DomainModelXImportSectionNamespaceScopeProvider.s_imports.contains(qname.append("service"));
      boolean _not_2 = (!_contains_2);
      if (_not_2) {
        _xifexpression = DomainModelXImportSectionNamespaceScopeProvider.s_imports.add(qname.append("service"));
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
  
  @Override
  public List<ImportNormalizer> getImplicitImports(final boolean ignoreCase) {
    final List<ImportNormalizer> predefinedImplicitImport = super.getImplicitImports(ignoreCase);
    ArrayList<ImportNormalizer> imports = new ArrayList<ImportNormalizer>();
    for (final QualifiedName key : DomainModelXImportSectionNamespaceScopeProvider.s_imports) {
      imports.add(this.doCreateImportNormalizer(key, true, ignoreCase));
    }
    imports.addAll(predefinedImplicitImport);
    return imports;
  }
}
