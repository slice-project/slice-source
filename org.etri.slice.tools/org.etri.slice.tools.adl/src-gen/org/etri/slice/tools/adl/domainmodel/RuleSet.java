/**
 * generated by Xtext
 */
package org.etri.slice.tools.adl.domainmodel;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Rule Set</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.etri.slice.tools.adl.domainmodel.RuleSet#getName <em>Name</em>}</li>
 *   <li>{@link org.etri.slice.tools.adl.domainmodel.RuleSet#getGroup_id <em>Group id</em>}</li>
 *   <li>{@link org.etri.slice.tools.adl.domainmodel.RuleSet#getArtifact_id <em>Artifact id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.etri.slice.tools.adl.domainmodel.DomainmodelPackage#getRuleSet()
 * @model
 * @generated
 */
public interface RuleSet extends EObject
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see org.etri.slice.tools.adl.domainmodel.DomainmodelPackage#getRuleSet_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.etri.slice.tools.adl.domainmodel.RuleSet#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Group id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Group id</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Group id</em>' attribute.
   * @see #setGroup_id(String)
   * @see org.etri.slice.tools.adl.domainmodel.DomainmodelPackage#getRuleSet_Group_id()
   * @model
   * @generated
   */
  String getGroup_id();

  /**
   * Sets the value of the '{@link org.etri.slice.tools.adl.domainmodel.RuleSet#getGroup_id <em>Group id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Group id</em>' attribute.
   * @see #getGroup_id()
   * @generated
   */
  void setGroup_id(String value);

  /**
   * Returns the value of the '<em><b>Artifact id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Artifact id</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Artifact id</em>' attribute.
   * @see #setArtifact_id(String)
   * @see org.etri.slice.tools.adl.domainmodel.DomainmodelPackage#getRuleSet_Artifact_id()
   * @model
   * @generated
   */
  String getArtifact_id();

  /**
   * Sets the value of the '{@link org.etri.slice.tools.adl.domainmodel.RuleSet#getArtifact_id <em>Artifact id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Artifact id</em>' attribute.
   * @see #getArtifact_id()
   * @generated
   */
  void setArtifact_id(String value);

} // RuleSet