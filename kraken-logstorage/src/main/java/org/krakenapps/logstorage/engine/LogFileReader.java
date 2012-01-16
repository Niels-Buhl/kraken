/*
 * Copyright 2011 Future Systems
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.krakenapps.logstorage.engine;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;

import org.krakenapps.logstorage.engine.v1.LogFileReaderV1;
import org.krakenapps.logstorage.engine.v2.LogFileReaderV2;

public abstract class LogFileReader {
	public static LogFileReader getLogFileReader(File indexPath, File dataPath) throws InvalidLogFileHeaderException, IOException {
		LogFileReader reader = null;
		RandomAccessFile indexHeaderReader = null;
		RandomAccessFile dataHeaderReader = null;

		try {
			indexHeaderReader = new RandomAccessFile(indexPath, "r");
			dataHeaderReader = new RandomAccessFile(dataPath, "r");
			LogFileHeader indexHeader = LogFileHeader.extractHeader(indexHeaderReader);
			LogFileHeader dataHeader = LogFileHeader.extractHeader(dataHeaderReader);

			if (indexHeader.version() != dataHeader.version())
				throw new InvalidLogFileHeaderException("different log version index and data file");

			if (indexHeader.version() == 1)
				reader = new LogFileReaderV1(indexPath, dataPath);
			else if (indexHeader.version() == 2)
				reader = new LogFileReaderV2(indexPath, dataPath);
			else
				throw new InvalidLogFileHeaderException("unsupported log version");
		} finally {
			if (indexHeaderReader != null)
				indexHeaderReader.close();
			if (dataHeaderReader != null)
				dataHeaderReader.close();
		}

		return reader;
	}

	public abstract LogRecord find(int id) throws IOException;

	public abstract void traverse(int limit, LogRecordCallback callback) throws IOException, InterruptedException;

	public abstract void traverse(int offset, int limit, LogRecordCallback callback) throws IOException, InterruptedException;

	public abstract void traverse(Date from, Date to, int limit, LogRecordCallback callback) throws IOException, InterruptedException;

	public abstract void traverse(Date from, Date to, int offset, int limit, LogRecordCallback callback) throws IOException,
			InterruptedException;

	public abstract void close() throws IOException;
}
