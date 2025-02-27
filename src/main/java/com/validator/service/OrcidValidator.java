package com.validator.service;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.regex.Pattern;

@Singleton
public class OrcidValidator {
    private static final Pattern ORCID_PATTERN = Pattern.compile("^\\d{4}-\\d{4}-\\d{4}-\\d{3}[\\dX]$");
    
    @Inject
    public OrcidValidator() {}

    public boolean isValid(String orcid) {
        if (orcid == null || !ORCID_PATTERN.matcher(orcid).matches()) {
            return false;
        }
        
        // Check checksum
        String digitsOnly = orcid.replace("-", "").toUpperCase();
        int total = 0;
        for (int i = 0; i < 15; i++) {
            int digit = Character.getNumericValue(digitsOnly.charAt(i));
            total = (total + digit) * 2;
        }
        int remainder = total % 11;
        int result = (12 - remainder) % 11;
        char checksum = result == 10 ? 'X' : (char) (result + '0');
        
        return checksum == digitsOnly.charAt(15);
    }
}
