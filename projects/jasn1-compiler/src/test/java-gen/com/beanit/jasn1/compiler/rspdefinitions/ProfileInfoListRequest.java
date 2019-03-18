/**
 * This class file was automatically generated by jASN1 v1.11.1-SNAPSHOT (http://www.beanit.com)
 */

package com.beanit.jasn1.compiler.rspdefinitions;

import java.io.IOException;
import java.io.EOFException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.io.Serializable;
import com.beanit.jasn1.ber.*;
import com.beanit.jasn1.ber.types.*;
import com.beanit.jasn1.ber.types.string.*;

import com.beanit.jasn1.compiler.pkix1explicit88.Certificate;
import com.beanit.jasn1.compiler.pkix1explicit88.CertificateList;
import com.beanit.jasn1.compiler.pkix1explicit88.Time;
import com.beanit.jasn1.compiler.pkix1implicit88.SubjectKeyIdentifier;

public class ProfileInfoListRequest implements BerType, Serializable {

	private static final long serialVersionUID = 1L;

	public static class SearchCriteria implements BerType, Serializable {

		private static final long serialVersionUID = 1L;

		public byte[] code = null;
		public OctetTo16 isdpAid = null;
		public Iccid iccid = null;
		public ProfileClass profileClass = null;
		
		public SearchCriteria() {
		}

		public SearchCriteria(byte[] code) {
			this.code = code;
		}

		public SearchCriteria(OctetTo16 isdpAid, Iccid iccid, ProfileClass profileClass) {
			this.isdpAid = isdpAid;
			this.iccid = iccid;
			this.profileClass = profileClass;
		}

		public int encode(OutputStream reverseOS) throws IOException {

			if (code != null) {
				for (int i = code.length - 1; i >= 0; i--) {
					reverseOS.write(code[i]);
				}
				return code.length;
			}

			int codeLength = 0;
			if (profileClass != null) {
				codeLength += profileClass.encode(reverseOS, false);
				// write tag: CONTEXT_CLASS, PRIMITIVE, 21
				reverseOS.write(0x95);
				codeLength += 1;
				return codeLength;
			}
			
			if (iccid != null) {
				codeLength += iccid.encode(reverseOS, true);
				return codeLength;
			}
			
			if (isdpAid != null) {
				codeLength += isdpAid.encode(reverseOS, false);
				// write tag: APPLICATION_CLASS, PRIMITIVE, 15
				reverseOS.write(0x4F);
				codeLength += 1;
				return codeLength;
			}
			
			throw new IOException("Error encoding CHOICE: No element of CHOICE was selected.");
		}

		public int decode(InputStream is) throws IOException {
			return decode(is, null);
		}

		public int decode(InputStream is, BerTag berTag) throws IOException {

			int codeLength = 0;
			BerTag passedTag = berTag;

			if (berTag == null) {
				berTag = new BerTag();
				codeLength += berTag.decode(is);
			}

			if (berTag.equals(BerTag.APPLICATION_CLASS, BerTag.PRIMITIVE, 15)) {
				isdpAid = new OctetTo16();
				codeLength += isdpAid.decode(is, false);
				return codeLength;
			}

			if (berTag.equals(Iccid.tag)) {
				iccid = new Iccid();
				codeLength += iccid.decode(is, false);
				return codeLength;
			}

			if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 21)) {
				profileClass = new ProfileClass();
				codeLength += profileClass.decode(is, false);
				return codeLength;
			}

			if (passedTag != null) {
				return 0;
			}

			throw new IOException("Error decoding CHOICE: Tag " + berTag + " matched to no item.");
		}

		public void encodeAndSave(int encodingSizeGuess) throws IOException {
			ReverseByteArrayOutputStream reverseOS = new ReverseByteArrayOutputStream(encodingSizeGuess);
			encode(reverseOS);
			code = reverseOS.getArray();
		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			appendAsString(sb, 0);
			return sb.toString();
		}

		public void appendAsString(StringBuilder sb, int indentLevel) {

			if (isdpAid != null) {
				sb.append("isdpAid: ").append(isdpAid);
				return;
			}

			if (iccid != null) {
				sb.append("iccid: ").append(iccid);
				return;
			}

			if (profileClass != null) {
				sb.append("profileClass: ").append(profileClass);
				return;
			}

			sb.append("<none>");
		}

	}

	public static final BerTag tag = new BerTag(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 45);

	public byte[] code = null;
	public SearchCriteria searchCriteria = null;
	public BerOctetString tagList = null;
	
	public ProfileInfoListRequest() {
	}

	public ProfileInfoListRequest(byte[] code) {
		this.code = code;
	}

	public ProfileInfoListRequest(SearchCriteria searchCriteria, BerOctetString tagList) {
		this.searchCriteria = searchCriteria;
		this.tagList = tagList;
	}

	public int encode(OutputStream reverseOS) throws IOException {
		return encode(reverseOS, true);
	}

	public int encode(OutputStream reverseOS, boolean withTag) throws IOException {

		if (code != null) {
			for (int i = code.length - 1; i >= 0; i--) {
				reverseOS.write(code[i]);
			}
			if (withTag) {
				return tag.encode(reverseOS) + code.length;
			}
			return code.length;
		}

		int codeLength = 0;
		int sublength;

		if (tagList != null) {
			codeLength += tagList.encode(reverseOS, false);
			// write tag: APPLICATION_CLASS, PRIMITIVE, 28
			reverseOS.write(0x5C);
			codeLength += 1;
		}
		
		if (searchCriteria != null) {
			sublength = searchCriteria.encode(reverseOS);
			codeLength += sublength;
			codeLength += BerLength.encodeLength(reverseOS, sublength);
			// write tag: CONTEXT_CLASS, CONSTRUCTED, 0
			reverseOS.write(0xA0);
			codeLength += 1;
		}
		
		codeLength += BerLength.encodeLength(reverseOS, codeLength);

		if (withTag) {
			codeLength += tag.encode(reverseOS);
		}

		return codeLength;

	}

	public int decode(InputStream is) throws IOException {
		return decode(is, true);
	}

	public int decode(InputStream is, boolean withTag) throws IOException {
		int codeLength = 0;
		int subCodeLength = 0;
		BerTag berTag = new BerTag();

		if (withTag) {
			codeLength += tag.decodeAndCheck(is);
		}

		BerLength length = new BerLength();
		codeLength += length.decode(is);

		int totalLength = length.val;
		if (totalLength == -1) {
			subCodeLength += berTag.decode(is);

			if (berTag.tagNumber == 0 && berTag.tagClass == 0 && berTag.primitive == 0) {
				int nextByte = is.read();
				if (nextByte != 0) {
					if (nextByte == -1) {
						throw new EOFException("Unexpected end of input stream.");
					}
					throw new IOException("Decoded sequence has wrong end of contents octets");
				}
				codeLength += subCodeLength + 1;
				return codeLength;
			}
			if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 0)) {
				subCodeLength += length.decode(is);
				searchCriteria = new SearchCriteria();
				int choiceDecodeLength = searchCriteria.decode(is, null);
				if (choiceDecodeLength != 0) {
					subCodeLength += choiceDecodeLength;
					subCodeLength += berTag.decode(is);
				}
				else {
					searchCriteria = null;
				}

			}
			if (berTag.tagNumber == 0 && berTag.tagClass == 0 && berTag.primitive == 0) {
				int nextByte = is.read();
				if (nextByte != 0) {
					if (nextByte == -1) {
						throw new EOFException("Unexpected end of input stream.");
					}
					throw new IOException("Decoded sequence has wrong end of contents octets");
				}
				codeLength += subCodeLength + 1;
				return codeLength;
			}
			if (berTag.equals(BerTag.APPLICATION_CLASS, BerTag.PRIMITIVE, 28)) {
				tagList = new BerOctetString();
				subCodeLength += tagList.decode(is, false);
				subCodeLength += berTag.decode(is);
			}
			int nextByte = is.read();
			if (berTag.tagNumber != 0 || berTag.tagClass != 0 || berTag.primitive != 0
			|| nextByte != 0) {
				if (nextByte == -1) {
					throw new EOFException("Unexpected end of input stream.");
				}
				throw new IOException("Decoded sequence has wrong end of contents octets");
			}
			codeLength += subCodeLength + 1;
			return codeLength;
		}

		codeLength += totalLength;

		if (totalLength == 0) {
			return codeLength;
		}
		subCodeLength += berTag.decode(is);
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 0)) {
			subCodeLength += length.decode(is);
			searchCriteria = new SearchCriteria();
			subCodeLength += searchCriteria.decode(is, null);
			if (subCodeLength == totalLength) {
				return codeLength;
			}
			subCodeLength += berTag.decode(is);
		}
		
		if (berTag.equals(BerTag.APPLICATION_CLASS, BerTag.PRIMITIVE, 28)) {
			tagList = new BerOctetString();
			subCodeLength += tagList.decode(is, false);
			if (subCodeLength == totalLength) {
				return codeLength;
			}
		}
		throw new IOException("Unexpected end of sequence, length tag: " + totalLength + ", actual sequence length: " + subCodeLength);

		
	}

	public void encodeAndSave(int encodingSizeGuess) throws IOException {
		ReverseByteArrayOutputStream reverseOS = new ReverseByteArrayOutputStream(encodingSizeGuess);
		encode(reverseOS, false);
		code = reverseOS.getArray();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		appendAsString(sb, 0);
		return sb.toString();
	}

	public void appendAsString(StringBuilder sb, int indentLevel) {

		sb.append("{");
		boolean firstSelectedElement = true;
		if (searchCriteria != null) {
			sb.append("\n");
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			sb.append("searchCriteria: ");
			searchCriteria.appendAsString(sb, indentLevel + 1);
			firstSelectedElement = false;
		}
		
		if (tagList != null) {
			if (!firstSelectedElement) {
				sb.append(",\n");
			}
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			sb.append("tagList: ").append(tagList);
			firstSelectedElement = false;
		}
		
		sb.append("\n");
		for (int i = 0; i < indentLevel; i++) {
			sb.append("\t");
		}
		sb.append("}");
	}

}
