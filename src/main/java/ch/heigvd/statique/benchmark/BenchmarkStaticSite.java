package ch.heigvd.statique.benchmark;
import ch.heigvd.statique.parser.MDParser;
import org.openjdk.jmh.annotations.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)

public class BenchmarkStaticSite {

    MDParser parser;
    String userPath;
    File input;
    File output;

    @Benchmark
    public void benchmark() throws IOException {


        parser = new MDParser(userPath);
        output = new File("output.md");
        userPath = output.getPath();

        FileWriter fw = new FileWriter(userPath);

        for (String row: parser.getResultHTML()) {
            fw.append(row);
        }

        fw.close();
    }

    @Setup
    public void setup() throws IOException {

        String example = "# Sample Markdown\n" +
                "\n" +
                "This is some basic, sample markdown.\n" +
                "\n" +
                "## Second Heading\n" +
                "\n" +
                " * Unordered lists, and:\n" +
                "  1. One\n" +
                "  1. Two\n" +
                "  1. Three\n" +
                " * More\n" +
                "\n" +
                "> Blockquote\n" +
                "\n" +
                "And **bold**, *italics*, and even *italics and later **bold***. Even ~~strikethrough~~. [A link](https://markdowntohtml.com) to somewhere.\n" +
                "\n" +
                "And code highlighting:\n" +
                "\n" +
                "```js\n" +
                "var foo = 'bar';\n" +
                "\n" +
                "function baz(s) {\n" +
                "   return foo + ':' + s;\n" +
                "}\n" +
                "```\n" +
                "\n" +
                "Or inline code like `var foo = 'bar';`.\n" +
                "\n" +
                "Or an image of bears\n" +
                "\n" +
                "![bears](http://placebear.com/200/200)\n" +
                "\n" +
                "The end ...\n";

        input = new File("input.md");
        userPath = input.getPath();

        FileWriter fw = new FileWriter(userPath);
        fw.write(example);

        fw.close();
    }

    @TearDown
    public void tearDown() throws IOException {
        if(input.exists())
            input.delete();
        if(output.exists())
            output.delete();
    }
}