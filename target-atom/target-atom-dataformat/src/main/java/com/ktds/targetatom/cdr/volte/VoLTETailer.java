package com.ktds.targetatom.cdr.volte;

import java.io.Serializable;

import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FixedLengthRecord(length=800, paddingChar=' ')
public class VoLTETailer implements Serializable {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	private static final long serialVersionUID = 1L;
	
	@DataField(pos=1, length=48, trim=true, align="L")
	String file_name;
	
	@DataField(pos=49, length=12, trim=true, align="L")
	String file_creation_time;
	
	@DataField(pos=61, length=7, trim=true, align="L")
	String completion_block_no;
	
	@DataField(pos=68, length=7, trim=true, align="L")
	String total_block_no;
	
	@DataField(pos=75, length=7, trim=true, align="R", paddingChar='0')
	long total_no_of_records;
	
	@DataField(pos=82, length=7, trim=true, align="L")
	String blocksize;
	
	@DataField(pos=89, length=10, trim=true, align="R", paddingChar='0')
	long file_size;
	
	@DataField(pos=99, length=8, trim=true, align="L")
	String version;
	
	@DataField(pos=107, length=693, trim=true, align="L")
	String filler_trailer;
	
	@DataField(pos=800, length=1, trim=true, align="L")
	String record_type;
	
	public VoLTETailer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VoLTETailer(String file_name, String file_creation_time, String completion_block_no, String total_block_no,
			long total_no_of_records, String blocksize, long file_size, String version, String filler_trailer,
			String record_type) {
		super();
		this.file_name = file_name;
		this.file_creation_time = file_creation_time;
		this.completion_block_no = completion_block_no;
		this.total_block_no = total_block_no;
		this.total_no_of_records = total_no_of_records;
		this.blocksize = blocksize;
		this.file_size = file_size;
		this.version = version;
		this.filler_trailer = filler_trailer;
		this.record_type = record_type;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getFile_creation_time() {
		return file_creation_time;
	}

	public void setFile_creation_time(String file_creation_time) {
		this.file_creation_time = file_creation_time;
	}

	public String getCompletion_block_no() {
		return completion_block_no;
	}

	public void setCompletion_block_no(String completion_block_no) {
		this.completion_block_no = completion_block_no;
	}

	public String getTotal_block_no() {
		return total_block_no;
	}

	public void setTotal_block_no(String total_block_no) {
		this.total_block_no = total_block_no;
	}

	public long getTotal_no_of_records() {
		return total_no_of_records;
	}

	public void setTotal_no_of_records(long total_no_of_records) {
		this.total_no_of_records = total_no_of_records;
	}

	public String getBlocksize() {
		return blocksize;
	}

	public void setBlocksize(String blocksize) {
		this.blocksize = blocksize;
	}

	public long getFile_size() {
		return file_size;
	}

	public void setFile_size(long file_size) {
		this.file_size = file_size;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getFiller_trailer() {
		return filler_trailer;
	}

	public void setFiller_trailer(String filler_trailer) {
		this.filler_trailer = filler_trailer;
	}

	public String getRecord_type() {
		return record_type;
	}

	public void setRecord_type(String record_type) {
		this.record_type = record_type;
	}

	public Logger getLog() {
		return log;
	}

	@Override
	public String toString() {
		return "VoLTETailer [file_name=" + file_name + ", file_creation_time=" + file_creation_time
				+ ", completion_block_no=" + completion_block_no + ", total_block_no=" + total_block_no
				+ ", total_no_of_records=" + total_no_of_records + ", blocksize=" + blocksize + ", file_size="
				+ file_size + ", version=" + version + ", filler_trailer=" + filler_trailer + ", record_type="
				+ record_type + "]";
	}
	
}
