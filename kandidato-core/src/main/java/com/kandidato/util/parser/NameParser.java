package com.kandidato.util.parser;

/**
 * Created by andriy on 1/11/15.
 */
public interface NameParser {

    public PersonName parseName();

    class PersonName {
        private final String firstName;
        private final String secondName;

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return secondName;
        }

        public PersonName(String firstName, String secondName) {
            this.firstName = firstName;
            this.secondName = secondName;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof PersonName)) return false;

            PersonName userName = (PersonName) o;

            if (!firstName.equals(userName.firstName)) return false;
            if (!secondName.equals(userName.secondName)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = firstName.hashCode();
            result = 31 * result + secondName.hashCode();
            return result;
        }
    }
}
