package com.ktds.targetatom.cdr.volte;

import java.io.Serializable;
import java.util.Date;

import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FixedLengthRecord(length=800, paddingChar=' ')
public class VoLTEHeader implements Serializable {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	private static final long serialVersionUID = 1L;
	
	@DataField(pos=1, length=4, trim=true, align="L")
	String tr_record;
	
	@DataField(pos=5, length=4, trim=true, align="L")
	String file_status;
	
	@DataField(pos=9, length=8, trim=true, align="L")
	String switching_office_name;
	
	@DataField(pos=17, length=8, trim=true, align="L")
	String bmd_id;
	
	@DataField(pos=25, length=8, trim=true, align="L")
	String hc_id;

	@DataField(pos=33, length=16, trim=true, align="L")
	String hc_address;
	
	@DataField(pos=49, length=4, trim=true, align="L")
	String file_seq_no;
	
	@DataField(pos=53, length=12, trim=true, align="L", pattern="yyyyMMddHHmm")
	Date file_creation_date;
	
	@DataField(pos=65, length=7, trim=true, align="L")
	String start_block_no;
	
	@DataField(pos=72, length=4, trim=true, align="L")
	String no_of_retrans;
	
	@DataField(pos=76, length=8, trim=true, align="L")
	String version;
	
	@DataField(pos=84, length=716, trim=true, align="L")
	String filler_head;
	
	@DataField(pos=800, length=1, trim=true, align="L")
	String record_type;
	
	public VoLTEHeader() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VoLTEHeader(String tr_record, String file_status, String switching_office_name, String bmd_id, String hc_id,
			String hc_address, String file_seq_no, Date file_creation_date, String start_block_no, String no_of_retrans,
			String version, String filler_head, String record_type) {
		super();
		this.tr_record = tr_record;
		this.file_status = file_status;
		this.switching_office_name = switching_office_name;
		this.bmd_id = bmd_id;
		this.hc_id = hc_id;
		this.hc_address = hc_address;
		this.file_seq_no = file_seq_no;
		this.file_creation_date = file_creation_date;
		this.start_block_no = start_block_no;
		this.no_of_retrans = no_of_retrans;
		this.version = version;
		this.filler_head = filler_head;
		this.record_type = record_type;
	}

	public String getTr_record() {
		return tr_record;
	}

	public void setTr_record(String tr_record) {
		this.tr_record = tr_record;
	}

	public String getFile_status() {
		return file_status;
	}

	public void setFile_status(String file_status) {
		this.file_status = file_status;
	}

	public String getSwitching_office_name() {
		return switching_office_name;
	}

	public void setSwitching_office_name(String switching_office_name) {
		this.switching_office_name = switching_office_name;
	}

	public String getBmd_id() {
		return bmd_id;
	}

	public void setBmd_id(String bmd_id) {
		this.bmd_id = bmd_id;
	}

	public String getHc_id() {
		return hc_id;
	}

	public void setHc_id(String hc_id) {
		this.hc_id = hc_id;
	}

	public String getHc_address() {
		return hc_address;
	}

	public void setHc_address(String hc_address) {
		this.hc_address = hc_address;
	}

	public String getFile_seq_no() {
		return file_seq_no;
	}

	public void setFile_seq_no(String file_seq_no) {
		this.file_seq_no = file_seq_no;
	}

	public Date getFile_creation_date() {
		return file_creation_date;
	}

	public void setFile_creation_date(Date file_creation_date) {
		this.file_creation_date = file_creation_date;
	}

	public String getStart_block_no() {
		return start_block_no;
	}

	public void setStart_block_no(String start_block_no) {
		this.start_block_no = start_block_no;
	}

	public String getNo_of_retrans() {
		return no_of_retrans;
	}

	public void setNo_of_retrans(String no_of_retrans) {
		this.no_of_retrans = no_of_retrans;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getFiller_head() {
		return filler_head;
	}

	public void setFiller_head(String filler_head) {
		this.filler_head = filler_head;
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
		return "VoLTEHeader [tr_record=" + tr_record + ", file_status=" + file_status + ", switching_office_name="
				+ switching_office_name + ", bmd_id=" + bmd_id + ", hc_id=" + hc_id + ", hc_address=" + hc_address
				+ ", file_seq_no=" + file_seq_no + ", file_creation_date=" + file_creation_date + ", start_block_no="
				+ start_block_no + ", no_of_retrans=" + no_of_retrans + ", version=" + version + ", filler_head="
				+ filler_head + ", record_type=" + record_type + "]";
	}
	
}
