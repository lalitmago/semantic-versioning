package org.tenx.assignment;

import java.util.Arrays;
import java.util.Optional;

public class VersionComparison {

    private String checkForPrereleaseAndBuildMetadataVersions(String input) {
        StringBuilder result = new StringBuilder();
        String[] temp;
        if(input.contains("-") || input.contains("+")) {
            if(input.contains("+")) {
                result.append(input.split("\\+")[0]);
            }
            if(input.contains("-")) {
                if(result.length() != 0) {
                    temp = result.toString().split("-");
                    if(temp.length > 2)
                        return "ERROR : Invalid input String, accepted format x.y.z with x, y, z > 0.";
                    else {
                        result.setLength(0);
                        result.append(temp[0]);
                    }
                } else {
                    temp = input.split("-");
                    if(temp.length > 2)
                        return "ERROR : Invalid input String, accepted format x.y.z with x, y, z > 0.";
                    else
                        result.append(temp[0]);
                }
            }
            return result.toString();
        } else
            return input;
    }

    private boolean checkForNonIntegers(String[] input) {
        try {
            Arrays.asList(input)
                    .forEach(Integer::parseInt);
        }catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private boolean checkForNegativeIntegers(String[] input) {

        Optional<String> result = Arrays.stream(input)
                .filter(x -> Integer.parseInt(x) < 0)
                .findAny();

        return !result.isPresent();
    }

    private String rationalise(String input) {

        String temp;
        String[] result = input.split("\\.");
        StringBuilder rationalisedString = new StringBuilder();

        if(result.length >= 3) {
            if (result.length > 3)
                return "ERROR : Invalid input String, accepted format x.y.z with x = major version, y = minor version and z = patch.";
            else if(result[2].contains("-") || result[2].contains("+")) {
                temp = checkForPrereleaseAndBuildMetadataVersions(result[2]);
                if(temp.contains("ERROR"))
                    return temp;
                else
                    result[2] = temp;
            }
        }

        if(!checkForNonIntegers(result))
            return "ERROR : Invalid input String, accepted format x.y.z with x, y, z > 0.";

        if(!checkForNegativeIntegers(result))
            return "ERROR : Invalid input String, accepted format x.y.z with x, y, z > 0.";

        if(result.length == 3) {

            rationalisedString.append(result[0]);
            rationalisedString.append(".");
            rationalisedString.append(result[1]);
            rationalisedString.append(".");
            rationalisedString.append(result[2]);
        }

        if(result.length < 3) {
            if(result.length == 2) {
                rationalisedString.append(result[0]);
                rationalisedString.append(".");
                rationalisedString.append(result[1]);
                rationalisedString.append(".0");
            }
            if(result.length == 1) {
                rationalisedString.append(result[0]);
                rationalisedString.append(".0.0");
            }
        }
        return rationalisedString.toString();
    }

    private int compareEachVersion(int one, int two) {
        if(one == two)
            return 0;
        else if (one > two)
            return 1;
        else
            return -1;
    }

    public String compareVersionStrings(String one, String two) {

        String rationalisedOne = rationalise(one.trim());
        if(rationalisedOne.contains("ERROR"))
            return one + " - " + rationalisedOne;
        String rationalisedTwo = rationalise(two.trim());
        if(rationalisedTwo.contains("ERROR"))
            return two + " - " + rationalisedTwo;

        String[] oneArray = rationalisedOne.split("\\.");
        String[] twoArray = rationalisedTwo.split("\\.");

        StringBuilder result = new StringBuilder();

        int countEquals = 0;
        int comparisonResult = 0;

        for(int i = 0; i < oneArray.length; i++) {

            comparisonResult = compareEachVersion(Integer.parseInt(oneArray[i]), Integer.parseInt(twoArray[i]));
            if(comparisonResult == 0)
                countEquals++;
            else if (comparisonResult == 1) {
                result.append(one);
                result.append(" > ");
                result.append(two);
                break;
            } else if (comparisonResult == -1) {
                result.append(two);
                result.append(" > ");
                result.append(one);
                break;
            }
        }

        if (countEquals == oneArray.length) {
            result.append(one);
            result.append(" = ");
            result.append(two);
        }
        return result.toString();
    }
}
