package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidParameterException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        finalSort(getPaths(args), args);
    }

    private static File sortIntAscending(File file1, File file2, int i) throws IOException, NumberFormatException, InvalidParameterException {
        var file3 = new File("outTemp" + i + ".txt");
        var isFileWritten = false;

        try (
                var fileWriter = new FileWriter(file3);
                var bfw = new BufferedWriter(fileWriter);
                var fileReader1 = new FileReader(file1);
                var fileReader2 = new FileReader(file2);
                var bfr1 = new BufferedReader(fileReader1);
                var bfr2 = new BufferedReader(fileReader2)
        ) {
            String line1 = "";
            String line2 = "";
            int previousLine1 = -2147483648;
            int previousLine2 = -2147483648;
            try {
                line1 = bfr1.readLine();
            } catch (IOException e) {
                throw new IOException("Error reading from" + file1);
            }
            try {
                line2 = bfr2.readLine();
            } catch (IOException e) {
                System.out.println("Error reading from " + file2);
                while (line1 != null) {
                    isFileWritten = writeLine(isFileWritten, bfw, line1);
                    line1 = bfr1.readLine();
                }
                return file3;
            }
            while (true) {
                if (line1 == null && line2 == null) {
                    break;
                } else if (line1 == null) {
                    while (line2 != null) {
                        int secondLine;
                        try {
                            secondLine = Integer.parseInt(line2);
                        } catch (NumberFormatException e) {
                            throw new NumberFormatException("value must be int!");
                        }
                        if (previousLine2 > secondLine) {
                            throw new InvalidParameterException("File " + file2 + " not sorted");
                        }
                        previousLine2 = secondLine;
                        isFileWritten = writeLine(isFileWritten, bfw, line2);
                        line2 = bfr2.readLine();
                    }
                    break;
                } else if (line2 == null) {
                    while (line1 != null) {
                        int firstLine;
                        try {
                            firstLine = Integer.parseInt(line1);
                        } catch (NumberFormatException e) {
                            throw new NumberFormatException("value must be int!");
                        }
                        if (previousLine1 > firstLine) {
                            throw new InvalidParameterException("File " + file1 + " not sorted");
                        }
                        previousLine1 = firstLine;
                        isFileWritten = writeLine(isFileWritten, bfw, line1);
                        line1 = bfr1.readLine();
                    }
                    break;
                }
                int firstLine = 0;
                int secondLine = 0;
                try {
                    firstLine = Integer.parseInt(line1);
                    if (previousLine1 > firstLine) {
                        throw new InvalidParameterException("File " + file1 + " not sorted");
                    }
                    previousLine1 = firstLine;
                } catch (NumberFormatException e) {
                    throw new NumberFormatException("value must be int!");
                }
                try {
                    secondLine = Integer.parseInt(line2);
                    if (previousLine2 > secondLine) {
                        throw new InvalidParameterException("File " + file2 + " not sorted");
                    }
                    previousLine2 = secondLine;
                } catch (NumberFormatException e) {
                    System.out.println("value must be int!");
                    while (line1 != null) {
                        isFileWritten = writeLine(isFileWritten, bfw, line1);
                        line1 = bfr1.readLine();
                    }
                    return file3;
                }
                if (firstLine <= secondLine) {
                    isFileWritten = writeLine(isFileWritten, bfw, line1);
                    line1 = bfr1.readLine();
                } else {
                    isFileWritten = writeLine(isFileWritten, bfw, line2);
                    line2 = bfr2.readLine();
                }
            }
        } catch (Exception e) {
            System.err.println(e);
            System.out.println("Произошла ошибка, сортировка не выполнена!");
        }
        return file3;

    }

    private static File sortIntDescending(File file1, File file2, int i) throws IOException, NumberFormatException, InvalidParameterException {
        var file3 = new File("outTemp" + i + ".txt");
        var isFileWritten = false;

        try (
                var fileWriter = new FileWriter(file3);
                var bfw = new BufferedWriter(fileWriter);
                var fileReader1 = new FileReader(file1);
                var fileReader2 = new FileReader(file2);
                var bfr1 = new BufferedReader(fileReader1);
                var bfr2 = new BufferedReader(fileReader2)
        ) {
            String line1 = "";
            String line2 = "";
            int previousLine1 = 2147483647;
            int previousLine2 = 2147483647;
            try {
                line1 = bfr1.readLine();
            } catch (IOException e) {
                throw new IOException("Error reading from" + file1);
            }
            try {
                line2 = bfr2.readLine();
            } catch (IOException e) {
                System.out.println("Error reading from " + file2);
                while (line1 != null) {
                    isFileWritten = writeLine(isFileWritten, bfw, line1);
                    line1 = bfr1.readLine();
                }
                return file3;
            }
            while (true) {
                if (line1 == null && line2 == null) {
                    break;
                } else if (line1 == null) {
                    while (line2 != null) {
                        int secondLine;
                        try {
                            secondLine = Integer.parseInt(line2);
                        } catch (NumberFormatException e) {
                            throw new NumberFormatException("value must be int!");
                        }
                        if (previousLine2 < secondLine) {
                            throw new InvalidParameterException("File " + file2 + " not sorted");
                        }
                        previousLine2 = secondLine;
                        isFileWritten = writeLine(isFileWritten, bfw, line2);
                        line2 = bfr2.readLine();
                    }
                    break;
                } else if (line2 == null) {
                    while (line1 != null) {
                        int firstLine;
                        try {
                            firstLine = Integer.parseInt(line1);
                        } catch (NumberFormatException e) {
                            throw new NumberFormatException("value must be int!");
                        }
                        if (previousLine1 < firstLine) {
                            throw new InvalidParameterException("File " + file1 + " not sorted");
                        }
                        previousLine1 = firstLine;
                        isFileWritten = writeLine(isFileWritten, bfw, line1);
                        line1 = bfr1.readLine();
                    }
                    break;
                }
                int firstLine = 0;
                int secondLine = 0;
                try {
                    firstLine = Integer.parseInt(line1);
                    if (previousLine1 < firstLine) {
                        throw new InvalidParameterException("File " + file1 + " not sorted");
                    }
                    previousLine1 = firstLine;
                } catch (NumberFormatException e) {
                    throw new NumberFormatException("value must be int!");
                }
                try {
                    secondLine = Integer.parseInt(line2);
                    if (previousLine1 < firstLine) {
                        throw new InvalidParameterException("File " + file1 + " not sorted");
                    }
                    previousLine2 = secondLine;
                } catch (NumberFormatException e) {
                    System.out.println("value must be int!");
                    while (line1 != null) {
                        isFileWritten = writeLine(isFileWritten, bfw, line1);
                        line1 = bfr1.readLine();
                    }
                    return file3;
                }
                if (firstLine >= secondLine) {
                    isFileWritten = writeLine(isFileWritten, bfw, line1);
                    line1 = bfr1.readLine();

                } else {
                    isFileWritten = writeLine(isFileWritten, bfw, line2);
                    line2 = bfr2.readLine();
                }
            }
        } catch (Exception e) {
            System.out.println("Произошла ошибка, сортировка не выполнена!");
        }
        return file3;

    }

    private static File sortStringAscending(File file1, File file2, int i) throws IOException, InvalidParameterException {
        var file3 = new File("outTemp" + i + ".txt");
        var isFileWritten = false;

        try (
                var fileWriter = new FileWriter(file3);
                var bfw = new BufferedWriter(fileWriter);
                var fileReader1 = new FileReader(file1);
                var fileReader2 = new FileReader(file2);
                var bfr1 = new BufferedReader(fileReader1);
                var bfr2 = new BufferedReader(fileReader2)
        ) {
            String line1 = "";
            String line2 = "";
            String previousLine1 = "";
            String previousLine2 = "";
            try {
                line1 = bfr1.readLine();
                previousLine1 = line1;
            } catch (IOException e) {
                throw new IOException("Error reading from" + file1);
            }
            try {
                line2 = bfr2.readLine();
                previousLine2 = line2;
            } catch (IOException e) {
                System.out.println("Error reading from " + file2);
                while (line1 != null) {
                    isFileWritten = writeLine(isFileWritten, bfw, line1);
                    line1 = bfr1.readLine();
                }
                return file3;
            }
            while (true) {
                if (line1 == null && line2 == null) {
                    break;
                } else if (line1 == null) {
                    while (line2 != null) {
                        if (previousLine2.compareTo(line2) > 0) {
                            throw new InvalidParameterException("File " + file2 + " not sorted");
                        }
                        previousLine2 = line2;
                        isFileWritten = writeLine(isFileWritten, bfw, line2);
                        line2 = bfr2.readLine();
                    }
                    break;
                } else if (line2 == null) {
                    while (line1 != null) {
                        if (previousLine1.compareTo(line1) > 0) {
                            throw new InvalidParameterException("File " + file1 + " not sorted");
                        }
                        previousLine1 = line1;
                        isFileWritten = writeLine(isFileWritten, bfw, line1);
                        line1 = bfr1.readLine();
                    }
                    break;
                }
                if (line1.compareTo(line2) < 0) {
                    isFileWritten = writeLine(isFileWritten, bfw, line1);
                    line1 = bfr1.readLine();
                    if (line1 != null) {
                        if (previousLine1.compareTo(line1) > 0) {
                            throw new InvalidParameterException("File " + file1 + " not sorted");
                        }
                        previousLine1 = line1;
                    }
                } else {
                    isFileWritten = writeLine(isFileWritten, bfw, line2);
                    line2 = bfr2.readLine();
                    if (line2 != null) {
                        if (previousLine2.compareTo(line2) > 0) {
                            throw new InvalidParameterException("File " + file2 + " not sorted");
                        }
                        previousLine2 = line2;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Произошла ошибка, сортировка не выполнена!");
        }
        return file3;
    }

    private static File sortStringDescending(File file1, File file2, int i) throws IOException, InvalidParameterException {
        var file3 = new File("outTemp" + i + ".txt");
        var isFileWritten = false;

        try (
                var fileWriter = new FileWriter(file3);
                var bfw = new BufferedWriter(fileWriter);
                var fileReader1 = new FileReader(file1);
                var fileReader2 = new FileReader(file2);
                var bfr1 = new BufferedReader(fileReader1);
                var bfr2 = new BufferedReader(fileReader2)
        ) {
            String line1 = "";
            String line2 = "";
            String previousLine1 = "";
            String previousLine2 = "";
            try {
                line1 = bfr1.readLine();
                previousLine1 = line1;
            } catch (IOException e) {
                throw new IOException("Error reading from" + file1);
            }
            try {
                line2 = bfr2.readLine();
                previousLine2 = line2;
            } catch (IOException e) {
                System.out.println("Error reading from " + file2);
                while (line1 != null) {
                    isFileWritten = writeLine(isFileWritten, bfw, line1);
                    line1 = bfr1.readLine();
                }
                return file3;
            }
            while (true) {
                if (line1 == null && line2 == null) {
                    break;
                } else if (line1 == null) {
                    while (line2 != null) {
                        if (previousLine2.compareTo(line2) < 0) {
                            throw new InvalidParameterException("File " + file2 + " not sorted");
                        }
                        previousLine2 = line2;
                        isFileWritten = writeLine(isFileWritten, bfw, line2);
                        line2 = bfr2.readLine();
                    }
                    break;
                } else if (line2 == null) {
                    while (line1 != null) {
                        if (previousLine1.compareTo(line1) < 0) {
                            throw new InvalidParameterException("File " + file1 + " not sorted");
                        }
                        previousLine1 = line1;
                        isFileWritten = writeLine(isFileWritten, bfw, line1);
                        line1 = bfr1.readLine();
                    }
                    break;
                }
                if (line1.compareTo(line2) > 0) {
                    isFileWritten = writeLine(isFileWritten, bfw, line1);
                    line1 = bfr1.readLine();
                    if (line1 != null) {
                        if (previousLine1.compareTo(line1) < 0) {
                            throw new InvalidParameterException("File " + file1 + " not sorted");
                        }
                        previousLine1 = line1;
                    }

                } else {
                    isFileWritten = writeLine(isFileWritten, bfw, line2);
                    line2 = bfr2.readLine();
                    if (line2 != null) {
                        if (previousLine2.compareTo(line2) < 0) {
                            throw new InvalidParameterException("File " + file1 + " not sorted");
                        }
                        previousLine2 = line2;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Произошла ошибка, сортировка не выполнена!");
        }
        return file3;
    }

    public static void finalSort(String[] paths, String[] args) {
        if ((args[0].equals("-a") && args[1].equals("-i") || (args[0].equals("-i")))) {
            File preout = null;
            int j = 0;
            while (true) {
                try {
                    preout = sortIntAscending(new File(paths[0]), new File(paths[1]), 1);
                    break;
                } catch (Exception e) {
                    j++;
                }
            }
            for (int i = j + 2; i < paths.length; i++) {
                try {
                    preout = sortIntAscending(preout, new File(paths[i]), i);
                } catch (Exception e) {
                    continue;
                }
                deleteAndRenameFiles(i, paths, preout, args);
            }
        } else if (args[0].equals("-d") && args[1].equals("-i")) {
            File preout = null;
            int j = 0;
            while (true) {
                try {
                    preout = sortIntDescending(new File(paths[j]), new File(paths[j + 1]), 1);
                    break;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            for (int i = j + 2; i < paths.length; i++) {
                try {
                    preout = sortIntDescending(preout, new File(paths[i]), i);
                } catch (Exception e) {
                    continue;
                }
                deleteAndRenameFiles(i, paths, preout, args);
            }
        } else if ((args[0].equals("-a") && args[1].equals("-s") || (args[0].equals("-s")))) {
            File preout = null;
            int j = 0;
            while (true) {
                try {
                    preout = sortStringAscending(new File(paths[j]), new File(paths[j + 1]), 1);
                    break;
                } catch (Exception e) {
                    j++;
                }
            }
            for (int i = j + 2; i < paths.length; i++) {
                try {
                    preout = sortStringAscending(preout, new File(paths[i]), i);
                } catch (Exception e) {
                    continue;
                }
                deleteAndRenameFiles(i, paths, preout, args);
            }
        } else if (args[0].equals("-d") && args[1].equals("-s")) {
            File preout = null;
            int j = 0;
            while (true) {
                try {
                    preout = sortStringDescending(new File(paths[j]), new File(paths[j + 1]), 1);
                    break;
                } catch (Exception e) {
                    j++;
                }
            }
            for (int i = j + 2; i < paths.length; i++) {
                try {
                    preout = sortStringDescending(preout, new File(paths[i]), i);
                } catch (Exception e) {
                    continue;
                }
                deleteAndRenameFiles(i, paths, preout, args);
            }
        }
    }


    private static void deleteAndRenameFiles(int i, String[] paths, File preout, String[] args) {
        if (i == paths.length - 1) {
            try {
                Files.deleteIfExists(Paths.get("out.txt"));
            } catch (IOException e) {
                System.out.println("Произошла ошибка!");
            }
            String path = null;
            if (args[0].equals("-i") || args[0].equals("-s")) {
                path = args[1];
            } else {
                path = args[2];
            }
            if (preout.renameTo(new File(path))) {
                System.out.println("Файл переименован");
            } else {
                System.out.println("Файл не переименован");
            }
            for (int j = 1; j < paths.length; j++) {
                try {
                    Files.deleteIfExists(Paths.get("outTemp" + j + ".txt"));
                } catch (IOException e) {
                    System.out.println("Произошла ошибка!");
                }
            }
        }
    }

    private static String[] getPaths(String[] args) {
        if (args[0].equals("-i") || args[0].equals("-s")) {
            String[] paths = new String[args.length - 2];
            for (int i = 2; i < args.length; i++) {
                paths[i - 2] = args[i];
            }
            return paths;
        } else {
            String[] paths = new String[args.length - 3];
            for (int i = 3; i < args.length; i++) {
                paths[i - 3] = args[i];
            }
            return paths;
        }
    }

    private static boolean writeLine(boolean isFileWritten, BufferedWriter bfw, String line) throws IOException {

        if (isFileWritten) {
            bfw.write("\n" + line);
        } else {
            isFileWritten = true;
            bfw.write(line);
        }
        return isFileWritten;
    }


}