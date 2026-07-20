package com.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class IOUtilsResourceCleanupTest {
	public static void main(String[] args) throws Exception {
		Path directory = Files.createTempDirectory("lawyersins-ioutils-");
		Path source = directory.resolve("source.txt");
		Path destination = directory.resolve("destination.txt");
		try {
			Files.write(source, "stream-cleanup".getBytes(StandardCharsets.UTF_8));
			try {
				IOUtils.copy(source.toFile(), directory.toFile());
				throw new AssertionError("Copying to a directory unexpectedly succeeded");
			} catch (IOException expected) {
				// The input stream must already be closed when output creation fails.
			}
			Files.delete(source);

			Files.write(source, "stream-cleanup".getBytes(StandardCharsets.UTF_8));
			IOUtils.copy(source.toFile(), destination.toFile());
			if(!IOUtils.fileExists(destination.toString()))
				throw new AssertionError("Copied file was not detected");
			if(IOUtils.fileExists(directory.toString()))
				throw new AssertionError("Directory was reported as a file");
			if(!"stream-cleanup".equals(new String(Files.readAllBytes(destination), StandardCharsets.UTF_8)))
				throw new AssertionError("Copied content was changed");
		} finally {
			Files.deleteIfExists(destination);
			Files.deleteIfExists(source);
			Files.deleteIfExists(directory);
		}
	}
}
