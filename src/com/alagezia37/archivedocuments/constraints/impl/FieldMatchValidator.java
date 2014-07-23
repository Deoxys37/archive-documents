package com.alagezia37.archivedocuments.constraints.impl;

import com.alagezia37.archivedocuments.constraints.FieldMatch;
import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object>
{
    private String firstFieldName;
    private String secondFieldName;
    private String errorFieldName;

    @Override
    public void initialize(final FieldMatch constraintAnnotation)
    {
        firstFieldName  = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
        errorFieldName  = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context)
    {
    	try {
			final Object firstObj  = BeanUtils.getProperty(value, firstFieldName);
			final Object secondObj = BeanUtils.getProperty(value, secondFieldName);
			
			boolean fieldsMatch = true;
			
			fieldsMatch = firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
			
			if(false == fieldsMatch) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(errorFieldName).addPropertyNode(secondFieldName).addConstraintViolation();
				
				return false;
			}
			
			return true;
		} catch (final Exception ignore) {
			// ignore
		}
		return true;
    }
}
