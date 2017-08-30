/*
 * Copyright 2004-2005 the original author or authors.
 *
 * Licensed under the LGPL license, Version 2.1 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.gnu.org/copyleft/lesser.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @author daquanda(liyingquan@gmail.com)
 * @author kevin(diamond_china@msn.com)
 */
package org.powerstone.util;

import java.io.StringReader;
import javax.servlet.http.HttpServletRequest;
import org.springframework.validation.commons.Resources;
import org.springframework.validation.Errors;

import org.apache.commons.validator.Field;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.ValidatorUtil;
/*
 * These are the source of antlr and struts dependencies
 */
import org.apache.struts.validator.validwhen.ValidWhenLexer;
import org.apache.struts.validator.validwhen.ValidWhenParser;

/**
 * This class contains the validwhen validation that is used in the
 * validator-rules.xml file.
 */
public class ValidWhen {

  /**
   * Returns true if <code>obj</code> is null or a String.
   */
  private static boolean isString(Object obj) {
    return (obj == null) ? true : String.class.isInstance(obj);
  }

  /**
   * Checks if the field matches the boolean expression specified in
   * <code>test</code> parameter.
   *
   * @param bean The bean validation is being performed on.
   *
   * @param va The <code>ValidatorAction</code> that is currently being
   * performed.
   *
   * @param field The <code>Field</code> object associated with the current
   * field being validated.
   *
   * @param errors The <code>ActionMessages</code> object to add errors to if any
   * validation errors occur.
   *
   * @param request Current request object.
   *
   * @return <code>true</code> if meets stated requirements,
   * <code>false</code> otherwise.
   */
  public static boolean validateValidWhen(
      Object bean,
      ValidatorAction va,
      Field field,
      Errors errors,
      Validator validator,
      HttpServletRequest request) {

    Object form = validator.getResource(org.apache.commons.validator.Validator.
                                        BEAN_KEY); ;
    String value = null;
    boolean valid = false;
    int index = -1;

    if (field.isIndexed()) {
      String key = field.getKey();

      final int leftBracket = key.indexOf("[");
      final int rightBracket = key.indexOf("]");

      if ( (leftBracket > -1) && (rightBracket > -1)) {
        index =
            Integer.parseInt(key.substring(leftBracket + 1, rightBracket));
      }
    }

    if (isString(bean)) {
      value = (String) bean;
    }
    else {
      value = ValidatorUtil.getValueAsString(bean, field.getProperty());
    }

    String test = field.getVarValue("test");
    if (test == null) {
      return false;
    }

    ValidWhenLexer lexer = new ValidWhenLexer(new StringReader(test));
    ValidWhenParser parser = new ValidWhenParser(lexer);

    parser.setForm(form);
    parser.setIndex(index);
    parser.setValue(value);

    try {
      parser.expression();
      valid = parser.getResult();
    }
    catch (Exception ex) {
      ex.printStackTrace();
      Resources.rejectValue(errors, field, va);
      return false;
    }

    if (!valid) {
      Resources.rejectValue(errors, field, va);
      return false;
    }
    return true;
  }

}
