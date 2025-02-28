package com.validator.service;

import com.validator.model.OrcidValidationResult;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Pattern;

@Singleton
public class OrcidValidator {
    private static final Pattern ORCID_PATTERN = Pattern.compile("^\\d{4}-\\d{4}-\\d{4}-\\d{3}[\\dX]$");
    private static final String ORCID_API_URL = "https://pub.orcid.org/v3.0/";
    private final HttpClient httpClient;

    @Inject
    public OrcidValidator() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public OrcidValidationResult validate(String orcid) {
        if (!isFormatValid(orcid)) {
            return new OrcidValidationResult(false, false, null);
        }

        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(ORCID_API_URL + orcid))
                .header("Accept", "application/json")
                .GET()
                .build();

            HttpResponse<String> response = httpClient.send(request, 
                HttpResponse.BodyHandlers.ofString());

            boolean exists = response.statusCode() == 200;
            String name = exists ? extractName(response.body()) : null;
            return new OrcidValidationResult(true, exists, name);
        } catch (Exception e) {
            return new OrcidValidationResult(true, false, null);
        }
    }

    private boolean isFormatValid(String orcid) {
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

    private String extractName(String jsonResponse) {
        // Simple JSON parsing - you might want to use a proper JSON library
        try {
            int nameStart = jsonResponse.indexOf("\"given-names\":{\"value\":\"") + 24;
            int nameEnd = jsonResponse.indexOf("\"", nameStart);
            String firstName = jsonResponse.substring(nameStart, nameEnd);

            int familyStart = jsonResponse.indexOf("\"family-name\":{\"value\":\"") + 24;
            int familyEnd = jsonResponse.indexOf("\"", familyStart);
            String lastName = jsonResponse.substring(familyStart, familyEnd);

            return firstName + " " + lastName;
        } catch (Exception e) {
            return null;
        }
    }
}
