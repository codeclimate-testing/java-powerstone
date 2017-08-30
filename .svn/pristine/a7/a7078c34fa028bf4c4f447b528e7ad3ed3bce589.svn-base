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

import org.apache.commons.validator.Field;
import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.ValidatorAction;
import org.apache.commons.validator.util.ValidatorUtils;
import org.springframework.validation.Errors;
import org.springmodules.commons.validator.FieldChecks;
import org.springframework.validation.ValidationUtils;
import org.springmodules.commons.validator.MessageUtils;

/**
 * ValidationUtil Helper class for performing custom validations that
 * aren't already included in the core Commons Validator.
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class ValidationUtil {
  //~ Methods ================================================================

  /**
   * Validates that two fields match.
   * @param bean
   * @param va
   * @param field
   * @param errors
   */
  public static boolean validateTwoFields(Object bean, ValidatorAction va,
                                          Field field, Errors errors) {
    String value =
        ValidatorUtils.getValueAsString(bean, field.getProperty());
    String sProperty2 = field.getVarValue("secondProperty");
    String value2 = ValidatorUtils.getValueAsString(bean, sProperty2);
    if (!GenericValidator.isBlankOrNull(value)) {
      try {
        if (!value.equals(value2)) {
          rejectValue(errors, field, va);
          return false;
        }
      }
      catch (Exception e) {
        rejectValue(errors, field, va);
        return false;
      }
    }

    return true;
  }

  protected static void rejectValue(Errors errors, Field field,
                                    ValidatorAction va) {
    String fieldCode = field.getKey();
    String errorCode = MessageUtils.getMessageKey(va, field);
    Object[] args = MessageUtils.getArgs(va, field);
    errors.rejectValue(fieldCode, errorCode, args, errorCode);
  }

}
