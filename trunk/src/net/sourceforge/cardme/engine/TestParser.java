package net.sourceforge.cardme.engine;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sourceforge.cardme.io.BinaryFoldingScheme;
import net.sourceforge.cardme.io.CompatibilityMode;
import net.sourceforge.cardme.io.FoldingScheme;
import net.sourceforge.cardme.io.VCardWriter;
import net.sourceforge.cardme.util.StringUtil;
import net.sourceforge.cardme.util.VCardUtils;
import net.sourceforge.cardme.vcard.VCard;
import net.sourceforge.cardme.vcard.VCardImpl;
import net.sourceforge.cardme.vcard.VCardVersion;
import net.sourceforge.cardme.vcard.errors.VCardError;
import net.sourceforge.cardme.vcard.features.EmailFeature;
import net.sourceforge.cardme.vcard.types.parameters.ParameterTypeStyle;

/**
 * Copyright 2011 George El-Haddad. All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met:
 * 
 *    1. Redistributions of source code must retain the above copyright notice, this list of
 *       conditions and the following disclaimer.
 * 
 *    2. Redistributions in binary form must reproduce the above copyright notice, this list
 *       of conditions and the following disclaimer in the documentation and/or other materials
 *       provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY GEORGE EL-HADDAD ''AS IS'' AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL GEORGE EL-HADDAD OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 * The views and conclusions contained in the software and documentation are those of the
 * authors and should not be interpreted as representing official policies, either expressed
 * or implied, of George El-Haddad.
 */

/**
 * 
 * @author George El-Haddad
 * <br/>
 * Mar 10, 2010
 * 
 * <p>A Test class to see how to use the VCardEngine.</p>
 */
public class TestParser {

	private static File[] vcardFiles = null;
	private VCardEngine vcardEngine = null;
	
	/**
	 * <p>Creates a new TestParser.</p>
	 */
	public TestParser() {
		vcardEngine = new VCardEngine();
	}
	
	/**
	 * <p>Sets the compatibility mode.</p>
	 *
	 * @param compatMode
	 */
	public void setCompatibilityMode(CompatibilityMode compatMode) {
		vcardEngine.setCompatibilityMode(compatMode);
	}
	
	/**
	 * <p>Retrieve all VCard files and then parse them.</p>
	 *
	 * @return {@link List}&lt;VCard&gt;
	 */
	public List<VCard> importVCards() {
		List<VCard> vcards = new ArrayList<VCard>();
		for(int i = 0; i < vcardFiles.length; i++) {
			try {
				VCard vcard = vcardEngine.parse(vcardFiles[i]);
				vcards.add(vcard);
			}
			catch(IOException ioe) {
				System.err.println("Could not read vcard file: "+vcardFiles[i].getAbsolutePath());
				ioe.printStackTrace();
			}
		}
		
		return vcards;
	}

//	Use command line arguments.
//	
//	/**
//	 * <p>Opens a file chooser dialog to select VCard files.</p>
//	 * 
//	 * @return {@link File}[]
//	 */
//	private File[] getFiles()
//	{
//		JFileChooser chooser = new JFileChooser();
//		chooser.setDialogTitle("Select VCards");
//		chooser.setCurrentDirectory(new File(System.getProperties().getProperty("user.home")));
//		chooser.setMultiSelectionEnabled(true);
//		chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
//			@Override
//			public boolean accept(File f) {
//				return f.getName().toLowerCase().endsWith(".vcf") || f.isDirectory();
//			}
//
//			public @Override
//			String getDescription() {
//
//				return "VCard Files";
//			}
//		});
//
//		int result = chooser.showOpenDialog(null);
//		if(result == JFileChooser.CANCEL_OPTION) {
//			return null;
//		}
//
//		try {
//			File[] files = chooser.getSelectedFiles(); // get the file
//			return files;
//		}
//		catch(Exception ex) {
//			JOptionPane.showMessageDialog(null, "Warning! Could not load the file(s)!", "Warning!", JOptionPane.WARNING_MESSAGE);
//			return null;
//		}
//	}

	/**
	 * <p>This is the main method. Here the TestParses in created and initialized.
	 * A VCardWriter is created to write the imported vcards to the System.out so
	 * we can see if everything got imported and written correctly.</p>
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		vcardFiles = new File[args.length];
		for (int i = 0; i < args.length; i++) {
			vcardFiles[i] = new File(args[i]);
		}
		
		TestParser testParser = new TestParser();
		testParser.setCompatibilityMode(CompatibilityMode.RFC2426);
//		testParser.setCompatibilityMode(CompatibilityMode.I_PHONE);
//		testParser.setCompatibilityMode(CompatibilityMode.KDE_ADDRESS_BOOK);
//		testParser.setCompatibilityMode(CompatibilityMode.MAC_ADDRESS_BOOK);
//		testParser.setCompatibilityMode(CompatibilityMode.MS_OUTLOOK);
		List<VCard> vcards = testParser.importVCards();
		
		VCardWriter writer = new VCardWriter();
		writer.setOutputVersion(VCardVersion.V3_0);
//		writer.setCompatibilityMode(CompatibilityMode.MS_OUTLOOK);
		writer.setCompatibilityMode(CompatibilityMode.RFC2426);
		writer.setFoldingScheme(FoldingScheme.MIME_DIR);
		writer.setBinaryfoldingScheme(BinaryFoldingScheme.MIME_DIR);
		writer.setEOL(VCardUtils.LF);
		
		for(int i = 0; i < vcards.size(); i++) {
			VCardImpl vcard = (VCardImpl)vcards.get(i);
			
			if(vcard.hasErrors()) {
				System.out.println("VCard "+i+" has some errors ...");
				List<VCardError> errors = vcard.getErrors();
				for(int j = 0; j < errors.size(); j++) {
					System.out.println(errors.get(j).getErrorMessage());
					System.out.println(errors.get(j).getSeverity());
					System.out.println(StringUtil.formatException(errors.get(j).getError()));
				}
			}
			
//			//Uncomment to change the output style of parameter list
//			
			Iterator<EmailFeature> iter = vcard.getEmails();
			while(iter.hasNext()) {
				iter.next().setParameterTypeStyle(ParameterTypeStyle.PARAMETER_VALUE_LIST);
			}
			
			writer.setVCard(vcard);
			String vstring = writer.buildVCardString();
			
			if(writer.hasErrors()) {
				List<VCardError> errors = vcard.getErrors();
				for(int j = 0; j < errors.size(); j++) {
					System.out.println(errors.get(j).getErrorMessage());
					System.out.println(errors.get(j).getSeverity());
					System.out.println(StringUtil.formatException(errors.get(j).getError()));
				}
			}
			
			System.out.println(vstring);
		}
		
		System.out.println("\n-- END --");
	}
}
