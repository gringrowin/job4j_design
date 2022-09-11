package ru.job4j.io;

public class CSVReader {

   /* public static void handle(ArgsName argsName) throws Exception {
        argsValid(argsName);
        List<List<String>> lineIn = new ArrayList<>();
        List<List<String>> lineOut = new ArrayList<>();
        Scanner scanner = new Scanner(new BufferedInputStream(new FileInputStream(argsName.get("path"))));
        while (scanner.hasNext()) {
            lineIn.add(List.of(scanner.nextLine().split(";")));
        }
        checkLineInAdd(lineIn);
        String[] filter = argsName.get("filter").split(",");
        int[] filterIndex = getIndexColumnAfterFilter(filter, lineIn);
        for (int index : filterIndex) {
            lineIn.stream()..;
        }
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(argsName.get("out"))))) {
            for (String s :   ) {
                out.println(s);
            }
        }   catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static int[] getIndexColumnAfterFilter(String[] filter, List<List<String>> lineIn) {
        int[] rsl = new int[filter.length];
        int i = 0;
        for (String f : filter) {
            int index = 0;
            for (String columnHeader : lineIn.get(0)) {
                if (columnHeader.equals(f)) {
                    rsl[i] = index;
                    i++;
                }
                index++;
            }
        }
        return rsl;
    }

    private static boolean checkLineInAdd(List<List<String>> lineIn) {
        if (lineIn.size() == 0) {
            throw new NoSuchElementException("Need check source file");
        }
        for (List<String> line : lineIn) {
            if (line.size() != lineIn.get(0).size()) {
                throw new NoSuchElementException("Need check source file structure");
            }
        }
        return true;
    }

    private static boolean argsValid(ArgsName argsName) {
        if (!Files.exists(Paths.get(argsName.get("path")))) {
            throw new IllegalArgumentException("Incorrect path");
        }
        if (!argsName.get("path").endsWith(".csv")) {
            throw new IllegalArgumentException("Incorrect source file extension");
        }
        if (!Pattern.matches("[;,]", argsName.get("delimiter"))) {
            throw new IllegalArgumentException("Incorrect source file extension");
        }
        if (!argsName.get("out").endsWith(".csv")
                && !argsName.get("out").equals("stdout")) {
            throw new IllegalArgumentException("Incorrect target path");
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        String data = String.join(
                System.lineSeparator(),
                "name;age;last_name;education",
                "Tom;20;Smith;Bachelor",
                "Jack;25;Johnson;Undergraduate",
                "William;30;Brown;Secondary special"
        );
        File file = new File("./data/source.csv");
        File target = new File("./data/target.csv");
        ArgsName argsName = ArgsName.of(new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=;", "-out=" + target.getAbsolutePath(), "-filter=name,age"
        });
        Files.writeString(file.toPath(), data);
        String expected = String.join(
                System.lineSeparator(),
                "name;age",
                "Tom;20",
                "Jack;25",
                "William;30"
        ).concat(System.lineSeparator());
        CSVReader.handle(argsName);
        System.out.println(Files.readString(target.toPath()));
    }
    */
}
