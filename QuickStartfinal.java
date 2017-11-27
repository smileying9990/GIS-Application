/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

/**
 *
 * @author Administrator
 */
import javax.swing.*;
import java.io.*;
import java.io.IOException;
import java.util.Vector;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.ResourceBundle;
import java.util.Locale;
import java.awt.*;
import java.awt.event.*;
import com.esri.mo2.ui.bean.*; // beans used: Map,Layer,Toc,TocAdapter,Tool
        // TocEvent,Legend(a legend is part of a toc),ActateLayer
import com.esri.mo2.ui.tb.ZoomPanToolBar;
import com.esri.mo2.ui.tb.SelectionToolBar;
import com.esri.mo2.ui.ren.LayerProperties;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import com.esri.mo2.data.feat.*; //ShapefileFolder, ShapefileWriter
import com.esri.mo2.map.dpy.FeatureLayer;
import com.esri.mo2.map.dpy.BaseFeatureLayer;
import com.esri.mo2.map.draw.*;
import com.esri.mo2.map.draw.BaseSimpleRenderer;
import com.esri.mo2.file.shp.*;
import com.esri.mo2.map.dpy.Layerset;
import com.esri.mo2.ui.bean.Tool;
import com.esri.mo2.ui.dlg.AboutBox;
import java.awt.geom.*;
import com.esri.mo2.cs.geom.*; //using Envelope, Point, BasePointsArray
import java.util.Iterator;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Timer;
import javax.swing.event.ListSelectionEvent;

public class QuickStartfinal extends JFrame {
      ResourceBundle names;                       
     Locale loc1 = new Locale("es","MX");
    Locale loc2 = new Locale("en","US");
    static Map map = new Map();
    static boolean fullMap = true; // Map not zoomed
    static boolean helpToolOn;
    static JTable jtable; // make jtable global
    Legend legend;
    Legend legend2;
    Layer layer = new Layer();
  Layer layer2 = new Layer();
  Layer layer22 = new Layer();
  Layer layer3 = null;
  Layer llayer = new Layer();
  Layer llayer2 = new Layer();
  Layer llayer22 = new Layer();
  JButton englishjb = new JButton("English");
  JButton spanishjb = new JButton("Espa\u00F1ol");
    static AcetateLayer acetLayer;
    static com.esri.mo2.map.dpy.Layer layer4;
    com.esri.mo2.map.dpy.Layer activeLayer;
    int activeLayerIndex;
     com.esri.mo2.cs.geom.Point initPoint,endPoint;
  double distance;
    JMenuBar mbar = new JMenuBar();
    JMenu file = new JMenu("File");
    JMenu theme = new JMenu("Theme");
    JMenu layercontrol = new JMenu("LayerControl");
     JMenu help = new JMenu("Help");
  JMenuItem attribitem = new JMenuItem("open attribute table",
                            new ImageIcon("C:\\esri\\MOJ20\\examples\\tableview.gif"));
  JMenuItem createlayeritem  = new JMenuItem("create layer from selection",
                    new ImageIcon("C:\\esri\\MOJ20\\examples\\Icon0915b.jpg"));
  static JMenuItem promoteitem = new JMenuItem("promote selected layer",
                    new ImageIcon("C:\\esri\\MOJ20\\examples\\promote1.gif"));
  JMenuItem demoteitem = new JMenuItem("demote selected layer",
                    new ImageIcon("C:\\esri\\MOJ20\\examples\\demote1.gif"));
  JMenuItem printitem = new JMenuItem("print",new ImageIcon("C:\\esri\\MOJ20\\examples\\print.gif"));
  JMenuItem addlyritem = new JMenuItem("add layer",new ImageIcon("C:\\esri\\MOJ20\\examples\\addtheme.gif"));
  JMenuItem remlyritem = new JMenuItem("remove layer",new ImageIcon("C:\\esri\\MOJ20\\examples\\delete.gif"));
  JMenuItem propsitem = new JMenuItem("Legend Editor",new ImageIcon("C:\\esri\\MOJ20\\examples\\properties.gif"));
  JMenu helptopics = new JMenu("Help Topics");
  JMenuItem tocitem = new JMenuItem("Table of Contents",new
ImageIcon("C:\\esri\\MOJ20\\examples\\helptopic.gif"));
  JMenuItem legenditem = new JMenuItem("Legend Editor",new ImageIcon("C:\\esri\\MOJ20\\examples\\help2.gif"));
  JMenuItem layercontrolitem = new JMenuItem("Layer Control",new
ImageIcon("C:\\esri\\MOJ20\\examples\\helptopic.gif"));
  JMenuItem helptoolitem = new JMenuItem("Help Tool",new ImageIcon("C:\\esri\\MOJ20\\examples\\help2.gif"));
  JMenuItem contactitem = new JMenuItem("Contact us");
  JMenuItem aboutitem = new JMenuItem("About MOJO...");
    static Toc toc = new Toc();
     String s1 = "C:\\ESRI\\MOJ20\\Samples\\Data\\USA\\states.shp";
  String s2 = "C:\\ESRI\\MOJ20\\Samples\\Data\\USA\\capitals.shp";
   String s3 = "C:\\ESRI\\MOJ20\\Samples\\Data\\USA\\24fit.shp";
   
   String s4="C:\\ESRI\\MOJ20\\Samples\\Data\\USA\\estados.shp";
   String s5= "C:\\ESRI\\MOJ20\\Samples\\Data\\USA\\capitales.shp";
    String datapathname = "";
    String legendname = "";
    ZoomPanToolBar zptb = new ZoomPanToolBar();
  static SelectionToolBar stb = new SelectionToolBar();
  JToolBar jtb = new JToolBar();
  ComponentListener complistener;
  JLabel statusLabel = new JLabel("status bar    LOC");
  static JLabel milesLabel = new JLabel("   DIST:  0 mi    ");
  static JLabel kmLabel = new JLabel("  0 km    ");
  java.text.DecimalFormat df = new java.text.DecimalFormat("0.000");
  JPanel myjp = new JPanel();
  JPanel myjp2 = new JPanel();
  
  JButton prtjb = new JButton(new ImageIcon("C:\\esri\\MOJ20\\examples\\print.gif"));
  JButton addlyrjb = new JButton(new ImageIcon("C:\\esri\\MOJ20\\examples\\addtheme.gif"));
  JButton ptrjb = new JButton(new ImageIcon("C:\\esri\\MOJ20\\examples\\pointer.gif"));
  JButton drawPtjb=new JButton(new ImageIcon("C:\\esri\\MOJ20\\examples\\draw_point_1.gif"));
  JButton distjb = new JButton(new ImageIcon("C:\\esri\\MOJ20\\examples\\measure_1.gif"));
  JButton XYjb = new JButton("XY");
  JButton hotjb = new JButton(new ImageIcon("C:\\esri\\MOJ20\\examples\\hotlink.gif"));
  JButton link = new JButton(new ImageIcon("C:\\esri\\MOJ20\\examples\\24icon.gif"));
  JButton checkbox= new JButton(new ImageIcon("C:\\esri\\MOJ20\\examples\\check.gif"));
  Toolkit tk = Toolkit.getDefaultToolkit();
  Image bolt = tk.getImage("C:\\esri\\MOJ20\\examples\\hotlink_32x32-32.gif");  // 32x32 gif file
  java.awt.Cursor boltCursor = tk.createCustomCursor(bolt,new java.awt.Point(6,30),"bolt");
  
  JButton helpjb = new JButton(new ImageIcon("C:\\esri\\MOJ20\\examples\\help2.gif"));
  Arrow arrow = new Arrow();
   DrawPoint drawPt = new DrawPoint();

  HelpTool helpTool = new HelpTool();
  //DistanceTool distanceTool= new DistanceTool();
  ActionListener lis;
  ActionListener lanlis;
  ActionListener layerlis;
  ActionListener layercontrollis;
  ActionListener helplis;
  TocAdapter mytocadapter;
  static Envelope env;

    //Suchi

    //it was local before and we made it class member to access elsewhere/ member functions as well as others
    AttrTab attrtab = null;

    static int mypick;
  Hotlink hotlink = new Hotlink(); 
    public QuickStartfinal() {
        super("Quick Start Help");
        helpToolOn = false;
        this.setBounds(40,40,700, 450);
        zptb.setMap(map);
        stb.setMap(map);
        setJMenuBar(mbar);
        ActionListener lisZoom = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                fullMap = false;
            }
        }; // can change a boolean here
        ActionListener lisFullExt = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                fullMap = true;
            }
        };
            MouseAdapter mlLisZoomActive = new MouseAdapter() {
          public void mousePressed(MouseEvent me) {
                if (SwingUtilities.isRightMouseButton(me) && helpToolOn) {
              try {
                          HelpDialog helpdialog = new HelpDialog((String)helpText.get(5));
                    helpdialog.setVisible(true);
          } catch(IOException e){}
            }
          }
    };
    MouseAdapter mlLisAddLayer = new MouseAdapter() {
          public void mousePressed(MouseEvent me) {
                if (SwingUtilities.isRightMouseButton(me) && helpToolOn) {
                  try {
                          HelpDialog helpdialog = new HelpDialog((String)helpText.get(6));
                    helpdialog.setVisible(true);
              } catch(IOException e){}
                }
          }
    };
        ActionListener clearSelection = new ActionListener() { // works!!  listens for eraser click
          // 'eraser' tool must be "enabled" in stb for this to fire
                  public void actionPerformed(ActionEvent ae) {
                    //System.out.println("Mission is go");
                    if(jtable!=null) {  //this is why jtable is global
                      ListSelectionModel selectionModel = jtable.getSelectionModel();
                      selectionModel.clearSelection();
                      // blue lines in table are now gone, but we must
                      // fix the SelectionSet so it is not null;  do that later
                    }
            //System.out.println("Praise the force");
                  }
        };
        // next line gets ahold of a reference to the zoomin button
        JButton zoomInButton = (JButton) zptb.getActionComponent("ZoomIn");
        JButton zoomFullExtentButton = (JButton) zptb
                .getActionComponent("ZoomToFullExtent");
        JButton zoomToSelectedLayerButton = (JButton) zptb
                .getActionComponent("ZoomToSelectedLayer");
        JButton clearSelectionButton =                        // add new pointer to eraser icon button
              (JButton)stb.getActionComponent("ClearSelection");
        zoomInButton.addActionListener(lisZoom);
        zoomFullExtentButton.addActionListener(lisFullExt);
        zoomToSelectedLayerButton.addActionListener(lisZoom);
        zoomToSelectedLayerButton.addMouseListener(mlLisZoomActive);
        addlyrjb.addMouseListener(mlLisAddLayer);
        clearSelectionButton.addActionListener(clearSelection);
        complistener = new ComponentAdapter() {
            public void componentResized(ComponentEvent ce) {
                if (fullMap) {
                    map.setExtent(env);
                    map.zoom(1.0); // scale is scale factor in pixels
                    map.redraw();
                }
            }
        };
        addComponentListener(complistener);
       lis = new ActionListener() {public void actionPerformed(ActionEvent ae){
          Object source = ae.getSource();
          if (source == prtjb || source instanceof JMenuItem ) {
        com.esri.mo2.ui.bean.Print mapPrint = new com.esri.mo2.ui.bean.Print();
        mapPrint.setMap(map);
        mapPrint.doPrint();// prints the map
        }
      else if (source == ptrjb) {
                arrow.arrowChores();
                map.setSelectedTool(arrow);
            }
      else if (source == drawPtjb) {  
          
          map.setSelectedTool(drawPt);
            }
          else if (source == distjb) {
                DistanceTool distanceTool = new DistanceTool();
                map.setSelectedTool(distanceTool);
        }
          else if (source == XYjb) {
                try {
                  AddXYtheme addXYtheme = new AddXYtheme();
                  addXYtheme.setMap(map);
                  addXYtheme.setVisible(false);// the file chooser needs a parent
                    // but the parent can stay behind the scenes
                  map.redraw();
                  } catch (IOException e){}
            }
           else if (source == checkbox) {
                                   
                   JComponent jcb=legend.getCheckBox();
                   Rectangle r = jcb.getVisibleRect();
                   jcb.setVisible(false);                                               
            }
                   
             else if (source == hotjb) {
        hotlink.setCursor(boltCursor); //set cursor for the tool
        map.setSelectedTool(hotlink);
        }
                else if (source == link) {
            try {
            java.awt.Desktop.getDesktop().open(new java.io.File("C:\\esri\\MOJ20\\examples\\24 .html"));
             } catch (IOException e){}
            }
                /*else if (source == link) {
            String osName=System.getProperty("os.name");
            System.out.println(osName);
            
            try {
              if(osName.startsWith("Windows")){
              Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler"+"http://www.24hourfitness.com");
             
              }
              }
              catch (Exception e){}
            }*/
          else if (source == helpjb) {
                helpToolOn = true;
               
                map.setSelectedTool(helpTool);
          }
		  
          
              
          else
            {
                try {
              AddLyrDialog aldlg = new AddLyrDialog();
              aldlg.setMap(map);
              aldlg.setVisible(true);
            } catch(IOException e){}
      }
    }};
	 lanlis = new ActionListener() {
          public void actionPerformed(ActionEvent ae){
                  Object source = ae.getSource();
                  if (source == englishjb) {
					  System.out.println("engbuttom");
                       
					   names = ResourceBundle.getBundle("NamesBundle",loc2);  //   (2)
                       
					   java.util.List list = toc.getAllLegends();
                        int count = list.size();
                        for (int j =0;j<count;j++) {              //remove old layers
                           com.esri.mo2.map.dpy.Layer dpylayer1 =
                                    (com.esri.mo2.map.dpy.Layer) ((Legend)list.get(j)).getLayer();
                         map.getLayerset().removeLayer(dpylayer1);
                   }
                   addShapefileToMap(layer,s1);
                 addShapefileToMap(layer2,s2);
                  addShapefileToMap(layer22,s3);
    
				 
              }
              else if (source == spanishjb) {
				  System.out.println("espbuttom");
            names = ResourceBundle.getBundle("NamesBundle",loc1);
            java.util.List list = toc.getAllLegends();
            int count = list.size();
            for (int j =0;j<count;j++) {              //remove old layers
              com.esri.mo2.map.dpy.Layer dpylayer1 =
                            (com.esri.mo2.map.dpy.Layer) ((Legend)list.get(j)).getLayer();
                          map.getLayerset().removeLayer(dpylayer1);
                    }
                    addShapefileToMap(llayer,s4);
            addShapefileToMap(llayer2,s5);
			addShapefileToMap(llayer22,s3);
			
                  }
              translate();
    }};
        layercontrollis = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String source = ae.getActionCommand();
                System.out.println(activeLayerIndex + " active index");
                if (source == "promote selected layer") {
                    map.getLayerset().moveLayer(activeLayerIndex,
                            ++activeLayerIndex);
                } else {
                    map.getLayerset().moveLayer(activeLayerIndex,
                            --activeLayerIndex);
                }
                enableDisableButtons();
                map.redraw();
            }
        };
        helplis = new ActionListener()
                        {public void actionPerformed(ActionEvent ae){
          Object source = ae.getSource();
          if (source instanceof JMenuItem) {
                String arg = ae.getActionCommand();
                if(arg == "About MOJO...") {
          AboutBox aboutbox = new AboutBox();
          aboutbox.setProductName("MOJO");
          aboutbox.setProductVersion("2.0");
          aboutbox.setVisible(true);
          aboutbox.setLocation(100,100);
            }
            else if(arg == "Contact us") {
                  try {
                String s = "\n\n\n\n                 Any enquiries should be addressed to " +
                "\n\n\n                         yingxue@rohan.sdsu.edu";
            HelpDialog helpdialog = new HelpDialog(s);
            helpdialog.setVisible(true);
          } catch(IOException e){}
            }
            else if(arg == "Table of Contents") {
                  try {
                HelpDialog helpdialog = new HelpDialog((String)helpText.get(0));
            helpdialog.setVisible(true);
          } catch(IOException e){}
            }
            else if(arg == "Legend Editor") {
                  try {
                HelpDialog helpdialog = new HelpDialog((String)helpText.get(1));
            helpdialog.setVisible(true);
          } catch(IOException e){}
            }
            else if(arg == "Layer Control") {
                  try {
                HelpDialog helpdialog = new HelpDialog((String)helpText.get(2));
            helpdialog.setVisible(true);
          } catch(IOException e){}
                }
                else if(arg == "Help Tool") {
              try {
            HelpDialog helpdialog = new HelpDialog((String)helpText.get(3));
            helpdialog.setVisible(true);
              } catch(IOException e){}
            }
          }
    }};
        layerlis = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                Object source = ae.getSource();
                if (source instanceof JMenuItem) {
                    String arg = ae.getActionCommand();
                    if (arg == "add layer") {
                        try {
                            AddLyrDialog aldlg = new AddLyrDialog();
                            aldlg.setMap(map);
                            aldlg.setVisible(true);
                        } catch (IOException e) {
                        }
                    } else if (arg == "remove layer") {
                        try {
                            com.esri.mo2.map.dpy.Layer dpylayer = legend
                                    .getLayer();
                            map.getLayerset().removeLayer(dpylayer);
                            map.redraw();
                            remlyritem.setEnabled(false);
                            propsitem.setEnabled(false);
                            attribitem.setEnabled(false);
                            promoteitem.setEnabled(false);
                            demoteitem.setEnabled(false);
                            stb.setSelectedLayer(null);
                            zptb.setSelectedLayer(null);
                            stb.setSelectedLayers(null);
                        } catch (Exception e) {
                        }
                    } else if (arg == "Legend Editor") {
                        LayerProperties lp = new LayerProperties();
                        lp.setLegend(legend);
                        lp.setSelectedTabIndex(0);
                        lp.setVisible(true);
                    } else if (arg == "open attribute table") {
                            //suchi
                        //problem: each time we call open, it used to open different windows
                        //now it brings the existing table to the front
                        //initially only attr table was created
                        layer4 = legend.getLayer();
                        try {
                            if(null != attrtab) {
                                //+ display any selections if any
                                attrtab.updateTableFromMapSelection();
                            } else {
                                 attrtab = new AttrTab();
                            }
                            //must be made visible to be shown
                            attrtab.setVisible(true);
                        } catch (IOException ex) {
                            Logger.getLogger(QuickStartfinal.class.getName()).log(Level.SEVERE,
null, ex);
                        }

                    } else if (arg == "create layer from selection") {
                        com.esri.mo2.map.draw.BaseSimpleRenderer sbr = new
com.esri.mo2.map.draw.BaseSimpleRenderer();
                        com.esri.mo2.map.draw.SimpleFillSymbol sfs = new
com.esri.mo2.map.draw.SimpleFillSymbol();// for
                        // polygons
                        sfs.setSymbolColor(new Color(255, 255, 0)); // mellow
                        // yellow
                        sfs.setType(com.esri.mo2.map.draw.SimpleFillSymbol.FILLTYPE_SOLID);
                        sfs.setBoundary(true);
                        layer4 = legend.getLayer();
                        FeatureLayer flayer2 = (FeatureLayer) layer4;
                                                // select, e.g., Montana and then click the
                        // create layer menuitem; next line verifies a selection
                        // was made
                        System.out.println("has selected"
                                + flayer2.hasSelection());
                        // next line creates the 'set' of selections
                        if (flayer2.hasSelection()) {
                            SelectionSet selectset = flayer2.getSelectionSet();
                                                        // next line makes a new feature layer of the
                            // selections
                            FeatureLayer selectedlayer = flayer2
                                    .createSelectionLayer(selectset);
                            sbr.setLayer(selectedlayer);
                            sbr.setSymbol(sfs);
                            selectedlayer.setRenderer(sbr);
                            Layerset layerset = map.getLayerset();
                                                        // next line places a new visible layer, e.g.
                            // Montana, on the map
                            layerset.addLayer(selectedlayer);
                            // selectedlayer.setVisible(true);
                            if (stb.getSelectedLayers() != null) {
                                promoteitem.setEnabled(true);
                            }
                            try {
                                legend2 = toc.findLegend(selectedlayer);
                            } catch (Exception e) {
                            }

                            CreateShapeDialog csd = new CreateShapeDialog(
                                    selectedlayer);
                            csd.setVisible(true);
                            Flash flash = new Flash(legend2);
                            flash.start();
                            map.redraw(); // necessary to see color immediately

                        }
                    }
                }
            }
        };
        toc.setMap(map);
        mytocadapter = new TocAdapter() {
            public void click(TocEvent e) {
                System.out.println(activeLayerIndex + "dex");
                legend = e.getLegend();
                activeLayer = legend.getLayer();
                stb.setSelectedLayer(activeLayer);
                zptb.setSelectedLayer(activeLayer);
                // get active layer index for promote and demote
                activeLayerIndex = map.getLayerset().indexOf(activeLayer);
                // layer indices are in order added, not toc order.
                System.out.println(activeLayerIndex + "active ndex");
                remlyritem.setEnabled(true);
                propsitem.setEnabled(true);
                attribitem.setEnabled(true);
                enableDisableButtons();
            }
        };
        map.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent me) {
                com.esri.mo2.cs.geom.Point worldPoint = null;
                if (map.getLayerCount() > 0) {
                    worldPoint = map.transformPixelToWorld(me.getX(), me.getY());
                    String s = "X:" + df.format(worldPoint.getX()) + " " + "Y:"
                            + df.format(worldPoint.getY());
                    statusLabel.setText(s);
                } else {
                    statusLabel.setText("X:0.000 Y:0.000");
                }
            }

        });

        //suchi: change attribute table selection when  map selection changes
        //sync must be there between the table and map highlight, so we
        //change table highlight on map selection
        // No API or feature as of now catches the map selection change event,
        // so we imitate the behavior as follows:
        // on a mouse click event on the map, the attribute table is refreshed.
        // Thus our links from layer to table only work when we select features
        // on the map layer using the SelcetFeatures tool in the SelectionToolBar
        map.addMouseListener(new MouseAdapter(){
            //event registerd on mouse release
            //timer is set so that selection tool has time to update the selection
            @Override
            public void mouseReleased(MouseEvent me) {
                                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        // Your database code here
                        if (null != attrtab) {
                            attrtab.updateTableFromMapSelection();
                        }
                    }
                }, 100);
                System.out.println("Got it 2");
            }
        });

        toc.addTocListener(mytocadapter);
        remlyritem.setEnabled(false); // assume no layer initially selected
        propsitem.setEnabled(false);
        attribitem.setEnabled(false);
        promoteitem.setEnabled(false);
        demoteitem.setEnabled(false);
        printitem.addActionListener(lis);
        addlyritem.addActionListener(layerlis);
        remlyritem.addActionListener(layerlis);
        propsitem.addActionListener(layerlis);
        attribitem.addActionListener(layerlis);
        createlayeritem.addActionListener(layerlis);
        promoteitem.addActionListener(layercontrollis);
        demoteitem.addActionListener(layercontrollis);
         tocitem.addActionListener(helplis);
    legenditem.addActionListener(helplis);
    layercontrolitem.addActionListener(helplis);
    helptoolitem.addActionListener(helplis);
    contactitem.addActionListener(helplis);
    aboutitem.addActionListener(helplis);
    hotjb.addActionListener(lis);
         file.add(addlyritem);
    file.add(printitem);
    file.add(remlyritem);
    file.add(propsitem);
    theme.add(attribitem);
    theme.add(createlayeritem);
    layercontrol.add(promoteitem);
    layercontrol.add(demoteitem);
    help.add(helptopics);
    helptopics.add(tocitem);
    helptopics.add(legenditem);
    helptopics.add(layercontrolitem);
    help.add(helptoolitem);
    help.add(contactitem);
    help.add(aboutitem);
    mbar.add(file);
    mbar.add(theme);
    mbar.add(layercontrol);
    mbar.add(help);
    prtjb.addActionListener(lis);
    prtjb.setToolTipText("print map");
    addlyrjb.addActionListener(lis);
    addlyrjb.setToolTipText("add layer");
    ptrjb.addActionListener(lis);
    drawPtjb.addActionListener(lis);
    distjb.addActionListener(lis);
    XYjb.addActionListener(lis);
    link.addActionListener(lis);
    checkbox.addActionListener(lis);
    helpjb.addActionListener(lis);
    XYjb.setToolTipText("add a layer of points from a file");
    link.setToolTipText("direct to website");
    checkbox.setToolTipText("set layer invisible or visible");
    ptrjb.setToolTipText("pointer");
    drawPtjb.setToolTipText("draw a point on the map");
    distjb.setToolTipText("press-drag-release to measure a distance");
     hotjb.setToolTipText("hotlink tool--click something to maybe see a picture");
    helpjb.setToolTipText("left click here, then right click on a tool to learn about that tool;"+
       "click arrow tool when done");
	   
     englishjb.addActionListener(lanlis);
        englishjb.setToolTipText("select english language");
        spanishjb.addActionListener(lanlis);
        spanishjb.setToolTipText("select spanish language");
    jtb.add(prtjb);
    jtb.add(addlyrjb);
    jtb.add(ptrjb);
    jtb.add(drawPtjb);
    jtb.add(distjb);
    jtb.add(XYjb);
 
    jtb.add(hotjb);
    jtb.add(link);
    jtb.add(checkbox);
    jtb.add(helpjb);
    myjp.add(jtb);
    myjp.add(zptb); myjp.add(stb);
    myjp2.add(statusLabel);
    myjp2.add(milesLabel);myjp2.add(kmLabel);
	myjp2.add(englishjb);
    myjp2.add(spanishjb);
    setuphelpText();
        getContentPane().add(map, BorderLayout.CENTER);
        getContentPane().add(myjp, BorderLayout.NORTH);
        getContentPane().add(myjp2, BorderLayout.SOUTH);
        addShapefileToMap(layer, s1);
        addShapefileToMap(layer2, s2);
        addShapefileToMap(layer22,s3);
        java.util.List list = toc.getAllLegends();
    
  
    com.esri.mo2.map.dpy.Layer lay1 = ((Legend)list.get(2)).getLayer();  //states layer
    com.esri.mo2.map.dpy.Layer lay0 = ((Legend)list.get(1)).getLayer();  //capitals layer
    com.esri.mo2.map.dpy.Layer lay2 = ((Legend)list.get(0)).getLayer(); 
     FeatureLayer flayer1 = (FeatureLayer)lay1;
    FeatureLayer flayer0 = (FeatureLayer)lay0;
    FeatureLayer flayer2 = (FeatureLayer)lay2;
    BaseSimpleRenderer bsr1 = (BaseSimpleRenderer)flayer1.getRenderer();
    BaseSimpleRenderer bsr0 = (BaseSimpleRenderer)flayer0.getRenderer();
    BaseSimpleRenderer bsr2 = (BaseSimpleRenderer)flayer2.getRenderer();
    SimplePolygonSymbol sym1 = (SimplePolygonSymbol)bsr1.getSymbol();
    SimpleMarkerSymbol sym0 = (SimpleMarkerSymbol)bsr0.getSymbol();
    RasterMarkerSymbol sym2 = new  RasterMarkerSymbol();
    SampleColorSchemes pastels = new SampleColorSchemes(SampleColorSchemes.PASTELS);
     sym1.setPaint(AoFillStyle.getPaint(com.esri.mo2.map.draw.AoFillStyle.SOLID_FILL,new
java.awt.Color(30,180,140)));
    sym0.setSymbolColor(java.awt.Color.red); //works for point layers
   sym2.setImageString( "C:\\esri\\MOJ20\\examples\\24.jpg" );
        sym2.setSizeX(16); 
        sym2.setSizeY(16);
        
    bsr1.setSymbol(sym1);
    bsr0.setSymbol(sym0);
     bsr2.setSymbol(sym2);
        getContentPane().add(toc, BorderLayout.WEST);
    }
      private void translate() {                              //    (3)
        file.setText(names.getString("File"));
        addlyritem.setText(names.getString("AddLayer"));
        remlyritem.setText(names.getString("RemoveLayer"));
        prtjb.setToolTipText(names.getString("Print"));
        addlyrjb.setToolTipText(names.getString("AddLayer"));
  }
    private void addShapefileToMap(Layer layer, String s) {
        String datapath = s; // "C:\\ESRI\\MOJ10\\Samples\\Data\\USA\\States.shp";
        layer.setDataset("0;" + datapath);
        map.add(layer);
    }
    private void setuphelpText() {
        String s0 =
          "    The toc, or table of contents, is to the left of the map. \n" +
          "    Each entry is called a 'legend' and represents a map 'layer' or \n" +
          "    'theme'.  If you click on a legend, that layer is called the \n" +
          "    active layer, or selected layer.  Its display (rendering) properties \n" +
          "    can be controlled using the Legend Editor, and the legends can be \n" +
          "    reordered using Layer Control.  Both Legend Editor and Layer Control \n" +
          "    are separate Help Topics.  This line is e... x... t... e... n... t... e...d"  +
          "    to test the scrollpane.";
        helpText.add(s0);
        String s1 = "  The Legend Editor is a menu item found under the File menu. \n" +
          "    Given that a layer is selected by clicking on its legend in the table of \n" +
          "    contents, clicking on Legend Editor will open a window giving you choices \n" +
          "    about how to display that layer.  For example you can control the color \n" +
          "    used to display the layer on the map, or whether to use multiple colors ";
        helpText.add(s1);
        String s2 = "  Layer Control is a Menu on the menu bar.  If you have selected a \n"+
           " layer by clicking on a legend in the toc (table of contents) to the left of \n" +
           " the map, then the promote and demote tools will become usable.  Clicking on \n" +
           " promote will raise the selected legend one position higher in the toc, and \n" +
           " clicking on demote will lower that legend one position in the toc.";
        helpText.add(s2);
        String s3 = "    This tool will allow you to learn about certain other tools. \n" +
          "    You begin with a standard left mouse button click on the Help Tool itself.\n" +
          "    RIGHT click on another tool and a window may give you information about the \n" +
          "    intended usage of the tool.  Click on the arrow tool to stop using the \n" +
          "    help tool.";
        helpText.add(s3);
        String s4 = "If you click on the Zoom In tool, and then click on the map, you \n" +
          " will see a part of the map in greater detail.  You can zoom in multiple times.\n" +
          " You can also sketch a rectangular part of the map, and zoom to that.  You can\n" +
          " undo a Zoom In with a Zoom Out or with a Zoom to Full Extent";
        helpText.add(s4);
        String s5 = "You must have a selected layer to use the Zoom to Active Layer tool.\n" +
          "    If you then click on Zoom to Active Layer, you will be shown enough of \n" +
          "    the full map to see all of the features in the layer you select.  If you \n" +
          "    select a layer that shows where glaciers are, then you do not need to \n" +
          "    see Hawaii, or any southern states, so you will see Alaska, and northern \n" +
          "    mainland states.";
        helpText.add(s5);
        String s6 = "If you click on the Add Layer tool, a window will pop up, in which you\n" +
          "need to use a file chooser to navigate to a shapefile definition. e.g. the shape\n" +
          "files in Samples\\Data\\USA.  Click on the one you want to add, e.g.uslakes.shp, \n" +
          "and then click on the OK button.  The new layer should appear on the map.";
        helpText.add(s6);

  }

    public static void main(String[] args) {
        QuickStartfinal qstart = new QuickStartfinal();

        qstart.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.out.println("Thanks, Mojo Multi-select exits");
                System.exit(0);
            }
        });
        qstart.setVisible(true);
        env = map.getExtent();
    }

    private void enableDisableButtons() {
        int layerCount = map.getLayerset().getSize();
        if (layerCount < 2) {
            promoteitem.setEnabled(false);
            demoteitem.setEnabled(false);
        } else if (activeLayerIndex == 0) {
            demoteitem.setEnabled(false);
            promoteitem.setEnabled(true);
        } else if (activeLayerIndex == layerCount - 1) {
            promoteitem.setEnabled(false);
            demoteitem.setEnabled(true);
        } else {
            promoteitem.setEnabled(true);
            demoteitem.setEnabled(true);
        }
    }
     private ArrayList helpText = new ArrayList(3);

    // following is an Add Layer dialog window
    class AddLyrDialog extends JDialog {

        Map map;
        ActionListener lis;
        JButton ok = new JButton("OK");
        JButton cancel = new JButton("Cancel");
        JPanel panel1 = new JPanel();
        com.esri.mo2.ui.bean.CustomDatasetEditor cus = new
com.esri.mo2.ui.bean.CustomDatasetEditor();

        AddLyrDialog() throws IOException {
            setBounds(50, 50, 520, 430);
            setTitle("Select a theme/layer");
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    setVisible(false);
                }
            });
            lis = new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    Object source = ae.getSource();
                    if (source == cancel) {
                        setVisible(false);
                    } else {
                        try {
                            setVisible(false);
                            map.getLayerset().addLayer(cus.getLayer());
                            map.redraw();
                            if (QuickStartfinal.stb.getSelectedLayers() != null) {
                                QuickStartfinal.promoteitem.setEnabled(true);
                            }
                        } catch (IOException e) {
                        }
                    }
                }
            };
            ok.addActionListener(lis);
            cancel.addActionListener(lis);
            getContentPane().add(cus, BorderLayout.CENTER);
            panel1.add(ok);
            panel1.add(cancel);
            getContentPane().add(panel1, BorderLayout.SOUTH);
        }

        public void setMap(com.esri.mo2.ui.bean.Map map1) {
            map = map1;
        }
    }

    class AttrTab extends JDialog {
      JPanel panel1 = new JPanel();
      com.esri.mo2.map.dpy.Layer layer = QuickStartfinal.layer4;

      //update map selection as per table selection
      AttrTableSelectionListener selectionListener =
          new AttrTableSelectionListener();
      public AttrTab() throws IOException {
                QuickStartfinal.jtable = new JTable(new MyTableModel());
                JTable jtable = QuickStartfinal.jtable;
                JScrollPane scroll = new JScrollPane(jtable);
        setBounds(300,500,650,350);
        setTitle("Attribute Table");
        addWindowListener(new WindowAdapter() {
          public void windowClosing(WindowEvent e) {
            setVisible(false);
            attrtab=null;
          }
        });
        FeatureLayer flayer6 = (FeatureLayer) legend.getLayer();
        if(!(flayer6.hasSelection())) {
                  //the next 5 lines select, just by code, all the features in a layer,
          // if nothing is currently selected!
          BaseQueryFilter qfilter = new BaseQueryFilter();
          FeatureLayer flayer2 = (FeatureLayer)legend.getLayer();
          FeatureClass fclass = flayer2.getFeatureClass();
          Fields fields = fclass.getFields();
          qfilter.setSubFields(fields);
          flayer2.setSelectedFeatures(qfilter); //have selected all features
          //the next three lines clear the selected set, but do NOT make it null
          BaseSelectionSet selectset = (BaseSelectionSet)flayer2.getSelectionSet();
          selectset.clear();
          flayer2.setSelectionSet(selectset);//try to clear selections
            //all of the above, while convoluted, works perfectly;
            //in AttrTableSelectListener, the selectset.add(caps) command now works
          // (selectset!=null) System.out.println("I am not null, buddy boy");
          //    else System.out.println("I am null, buddy boy");
        }
        // Add listener to jtable & update the map
        jtable.getSelectionModel().addListSelectionListener(selectionListener);
        // reverse: i.e.update the list from the map selection
        updateTableFromMapSelection();
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        // next line necessary for horiz scrollbar to work
        jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumn tc = null;
        int numCols = jtable.getColumnCount();
                for (int j = 0; j < numCols; j++) {
          tc = jtable.getColumnModel().getColumn(j);
          tc.setMinWidth(50);
        }
        getContentPane().add(scroll, BorderLayout.CENTER);
      }
      //highlight the rows in jtable according to map selection
      public void updateTableFromMapSelection() {//check if something is selected
        FeatureLayer flayer2 = (FeatureLayer) legend.getLayer();
        if (flayer2.hasSelection()){
          SelectionSet selectset = flayer2.getSelectionSet();
          jtable.setVisible(false);
          ListSelectionModel selectionModel = jtable.getSelectionModel();
          //clear the event listener
          selectionModel.removeListSelectionListener(selectionListener);
          //clear all the current highlights. e.g. CA -> NV
          selectionModel.clearSelection();
          //highlight the rows
          for (Iterator<BaseDataID> iter = selectset.iterator(); iter.hasNext();) {
            BaseDataID a = iter.next();
            selectionModel.addSelectionInterval(a.getID()-1, a.getID()-1);
          }
          jtable.updateUI();
          jtable.setVisible(true);
          selectionModel.addListSelectionListener(selectionListener);
                }
      }
      class AttrTableSelectionListener implements ListSelectionListener {
        @Override  //Compiler verifies something is overrridden
        public void valueChanged(ListSelectionEvent event) {
          //System.out.println("boy howdy");
          FeatureLayer flayer2 = (FeatureLayer) legend.getLayer();
          BaseSelectionSet selectset = (BaseSelectionSet)flayer2.getSelectionSet();
          //clearing a selection set does NOT make it null
          if (selectset==null){  // fending off null selectset after a 'clear'
                         BaseQueryFilter qfilter = new BaseQueryFilter();
                         FeatureClass fclass = flayer2.getFeatureClass();
                         Fields fields = fclass.getFields();
                         qfilter.setSubFields(fields);
                         flayer2.setSelectedFeatures(qfilter); //have selected all features
                         //the next three lines clear the selected set, but do NOT make it null
                         selectset= (BaseSelectionSet)flayer2.getSelectionSet();
                         selectset.clear();
                         flayer2.setSelectionSet(selectset);//try to clear selections
                         //all of the above, while convoluted, works perfectly;
                         //in AttrTableSelectListener, the selectset.add(caps) command now works
                  }
          int rowIndexStart = jtable.getSelectedRow(); // first selected row
                  int rowIndexEnd = jtable.getSelectionModel().getMaxSelectionIndex();// last
                  flayer2 = (FeatureLayer)legend.getLayer();
          selectset = (BaseSelectionSet)flayer2.getSelectionSet();
          if (flayer2.hasSelection())
            selectset.clear();
          for (int i = rowIndexStart; i <= rowIndexEnd; i++) {
            if (jtable.isRowSelected(i)) {
              BaseDataID caps = new BaseDataID("",i + 1);
              selectset.add(caps);// blows up if adding to null selectset
            }
          }
          //adds the new selection list to selection UI tool
          flayer2.setSelectionSet(selectset);
        }
      }
    }

    class MyTableModel extends AbstractTableModel {
                // the required methods to implement are getRowCount,
        // getColumnCount, getValueAt
        com.esri.mo2.map.dpy.Layer layer = QuickStartfinal.layer4;

        MyTableModel() {
            qfilter.setSubFields(fields);
            com.esri.mo2.data.feat.Cursor cursor = flayer.search(qfilter);
            while (cursor.hasMore()) {
                ArrayList inner = new ArrayList();
                Feature f = (com.esri.mo2.data.feat.Feature) cursor.next();
                inner.add(0, String.valueOf(row));
                for (int j = 1; j < fields.getNumFields(); j++) {
                    inner.add(f.getValue(j).toString());
                }
                data.add(inner);
                row++;
            }
        }

        FeatureLayer flayer = (FeatureLayer) layer;
        FeatureClass fclass = flayer.getFeatureClass();
        String columnNames[] = fclass.getFields().getNames();
        ArrayList data = new ArrayList();
        int row = 0;
        int col = 0;
        BaseQueryFilter qfilter = new BaseQueryFilter();
        Fields fields = fclass.getFields();

        public int getColumnCount() {
            return fclass.getFields().getNumFields();
        }

        public int getRowCount() {
            return data.size();
        }

        public String getColumnName(int colIndx) {
            return columnNames[colIndx];
        }

        public Object getValueAt(int row, int col) {
            ArrayList temp = new ArrayList();
            temp = (ArrayList) data.get(row);
            return temp.get(col);
        }
    }
    
    class AddXYtheme extends JDialog {
  Map map;
  Vector s2 = new Vector();
  Vector s3 = new Vector();
  JFileChooser jfc = new JFileChooser();
  BasePointsArray bpa = new BasePointsArray();
  FeatureLayer XYlayer;
  AddXYtheme() throws IOException {
        setBounds(50,50,520,430);
        jfc.showOpenDialog(this);
        try {
          File file  = jfc.getSelectedFile();
          FileReader fred = new FileReader(file);
          BufferedReader in = new BufferedReader(fred);
          String s; // = in.readLine();
          double x,y;
          int n = 0;
          while ((s = in.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(s,",");
                x = Double.parseDouble(st.nextToken());
                y = Double.parseDouble(st.nextToken());
                bpa.insertPoint(n,new com.esri.mo2.cs.geom.Point(x,y));
                s2.addElement(st.nextToken());
                s3.addElement(st.nextToken());
          }
        } catch (IOException e){}
        XYfeatureLayer xyfl = new XYfeatureLayer(bpa,map,s2,s3);
        XYlayer = xyfl;
        xyfl.setVisible(true);
        map = QuickStartfinal.map;
        map.getLayerset().addLayer(xyfl);
        map.redraw();
        CreateXYShapeDialog xydialog =
                              new CreateXYShapeDialog(XYlayer);
        xydialog.setVisible(true);
  }
  public void setMap(com.esri.mo2.ui.bean.Map map1){
          map = map1;
  }
    }
    class XYfeatureLayer extends BaseFeatureLayer {
  BaseFields fields;
  private java.util.Vector featureVector;
  public XYfeatureLayer(BasePointsArray bpa,Map map,Vector s2,Vector s3) {
        createFeaturesAndFields(bpa,map,s2,s3);
        BaseFeatureClass bfc = getFeatureClass("24Fit",bpa);
        setFeatureClass(bfc);
        BaseSimpleRenderer srd = new BaseSimpleRenderer();
        com.esri.mo2.map.draw.RasterMarkerSymbol rmSymbol = new RasterMarkerSymbol();
        rmSymbol.setImageString( "C:\\esri\\MOJ20\\examples\\24.jpg" );
        rmSymbol.setSizeX(16); 
        rmSymbol.setSizeY(16);
        srd.setSymbol(rmSymbol);
        setRenderer(srd);
        // without setting layer capabilities, the points will not
        // display (but the toc entry will still appear)
        XYLayerCapabilities lc = new XYLayerCapabilities();
        setCapabilities(lc);
  }
  private void createFeaturesAndFields(BasePointsArray bpa,Map map,Vector s2,Vector s3) {
        featureVector = new java.util.Vector();
        fields = new BaseFields();
        createDbfFields();
        for(int i=0;i<bpa.size();i++) {
          BaseFeature feature = new BaseFeature();  //feature is a row
          feature.setFields(fields);
          com.esri.mo2.cs.geom.Point p = new
            com.esri.mo2.cs.geom.Point(bpa.getPoint(i));
          feature.setValue(0,p);
          feature.setValue(1,new Integer(0));  // point data
          feature.setValue(2,(String)s2.elementAt(i));
          feature.setValue(3,(String)s3.elementAt(i));
         
          feature.setDataID(new BaseDataID("24Fit",i));
          featureVector.addElement(feature);
        }
  }
  private void createDbfFields() {
        fields.addField(new BaseField("#SHAPE#",Field.ESRI_SHAPE,0,0));
        fields.addField(new BaseField("ID",java.sql.Types.INTEGER,9,0));
        fields.addField(new BaseField("Name",java.sql.Types.VARCHAR,16,0));
        fields.addField(new BaseField("link",java.sql.Types.VARCHAR,50,0));
  }
  public BaseFeatureClass getFeatureClass(String name,BasePointsArray bpa){
    com.esri.mo2.map.mem.MemoryFeatureClass featClass = null;
    try {
          featClass = new com.esri.mo2.map.mem.MemoryFeatureClass(MapDataset.POINT,
            fields);
    } catch (IllegalArgumentException iae) {}
    featClass.setName(name);
    for (int i=0;i<bpa.size();i++) {
          featClass.addFeature((Feature) featureVector.elementAt(i));
    }
    return featClass;
  }
  private final class XYLayerCapabilities extends
       com.esri.mo2.map.dpy.LayerCapabilities {
    XYLayerCapabilities() {
          for (int i=0;i<this.size(); i++) {
                setAvailable(this.getCapabilityName(i),true);
                setEnablingAllowed(this.getCapabilityName(i),true);
                getCapability(i).setEnabled(true);
          }
    }
  }
}

    class CreateShapeDialog extends JDialog {

        String name = "";
        String path = "";
        JButton ok = new JButton("OK");
        JButton cancel = new JButton("Cancel");
        JTextField nameField = new JTextField(
                "enter layer name here, then hit ENTER", 25);
        com.esri.mo2.map.dpy.FeatureLayer selectedlayer;
        ActionListener lis = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                Object o = ae.getSource();
                if (o == nameField) {
                    name = nameField.getText().trim();
                    path = ((ShapefileFolder)
(QuickStartfinal.layer4.getLayerSource()))
                            .getPath();
                    System.out.println(path + "    " + name);
                } else if (o == cancel) {
                    setVisible(false);
                } else {
                    try {
                        ShapefileWriter.writeFeatureLayer(selectedlayer, path,
                                name, 2);
                    } catch (Exception e) {
                        System.out.println("write error");
                    }
                    setVisible(false);
                }
            }
        };

        JPanel panel1 = new JPanel();
        JLabel centerlabel = new JLabel();

        // centerlabel;
        CreateShapeDialog(com.esri.mo2.map.dpy.FeatureLayer layer5) {
            selectedlayer = layer5;
            setBounds(40, 350, 450, 150);
            setTitle("Create new shapefile?");
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    setVisible(false);
                }
            });
            nameField.addActionListener(lis);
            ok.addActionListener(lis);
            cancel.addActionListener(lis);
            String s = "<HTML> To make a new shapefile from the new layer, enter<BR>"
                    + "the new name you want for the layer and click OK.<BR>"
                    + "You can then add it to the map in the usual way.<BR>"
                    + "Click ENTER after replacing the text with your layer name";
            centerlabel.setHorizontalAlignment(JLabel.CENTER);
            centerlabel.setText(s);
            getContentPane().add(centerlabel, BorderLayout.CENTER);
            panel1.add(nameField);
            panel1.add(ok);
            panel1.add(cancel);
            getContentPane().add(panel1, BorderLayout.SOUTH);
        }
    }
    class CreateXYShapeDialog extends JDialog {
  String name = "";
  String path = "";
  JButton ok = new JButton("OK");
  JButton cancel = new JButton("Cancel");
  JTextField nameField = new JTextField("enter layer name here, then hit ENTER",35);
  JTextField pathField = new JTextField("enter full path name here, then hit ENTER",35);
  com.esri.mo2.map.dpy.FeatureLayer XYlayer;
  ActionListener lis = new ActionListener() {public void actionPerformed(ActionEvent
ae) {
        Object o = ae.getSource();
        if (o == pathField)
      path = pathField.getText().trim();
    else if (o == nameField) {
          name = nameField.getText().trim();
          //path = ((ShapefileFolder)(QuickStartfinal.layer4.getLayerSource())).getPath();
          System.out.println(path+"    " + name);
    }
        else if (o == cancel)
      setVisible(false);
        else {  // ok button clicked
          try {
                ShapefileWriter.writeFeatureLayer(XYlayer,path,name,0);
          } catch(Exception e) {System.out.println("write error");}
          setVisible(false);
    }
  }};

  JPanel panel1 = new JPanel();
  JPanel panel2 = new JPanel();
  JLabel centerlabel = new JLabel();
  //centerlabel;
  CreateXYShapeDialog (com.esri.mo2.map.dpy.FeatureLayer layer5) {
        XYlayer = layer5;
    setBounds(40,250,600,300);
    setTitle("Create new shapefile?");
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
            setVisible(false);
          }
    });
    nameField.addActionListener(lis);
    pathField.addActionListener(lis);
    ok.addActionListener(lis);
    cancel.addActionListener(lis);
    String s = "<HTML> To make a new shapefile from the new layer, enter<BR>" +
      "the new name you want for the layer and hit ENTER.<BR>" +
      "then enter a path to the folder you want to use <BR>" +
      "and hit ENTER once again <BR>" +
      "You can then add it to the map in the usual way.<BR>"+
      "Click ENTER after replacing the text with your layer name";
    centerlabel.setHorizontalAlignment(JLabel.CENTER);
    centerlabel.setText(s);
    //getContentPane().add(centerlabel,BorderLayout.CENTER);
    panel1.add(centerlabel);
    panel1.add(nameField);
    panel1.add(pathField);
    panel2.add(ok);
    panel2.add(cancel);
    getContentPane().add(panel2,BorderLayout.SOUTH);
    getContentPane().add(panel1,BorderLayout.CENTER);
  }
}
    class HelpDialog extends JDialog {
  JTextArea helptextarea;
  public HelpDialog(String inputText) throws IOException {
        setBounds(70,70,460,250);
          setTitle("Help");
          addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
              setVisible(false);
            }
    });
          helptextarea = new JTextArea(inputText,7,40);
          JScrollPane scrollpane = new JScrollPane(helptextarea);
    helptextarea.setEditable(false);
    getContentPane().add(scrollpane,"Center");
  }
}
class HelpTool extends Tool {
}

   class Arrow extends Tool {
  public void arrowChores() { // undo measure tool residue
    QuickStartfinal.milesLabel.setText("DIST   0 mi   ");
    QuickStartfinal.kmLabel.setText("   0 km    ");
    if (QuickStartfinal.acetLayer != null)
      QuickStartfinal.map.remove(QuickStartfinal.acetLayer);
    QuickStartfinal.acetLayer = null;
    QuickStartfinal.map.repaint();
    QuickStartfinal.helpToolOn = false;
  }
 }
   class DrawPoint extends Tool {
  Map map = QuickStartfinal.map;
  SimpleMarkerSymbol sms = new SimpleMarkerSymbol();
  com.esri.mo2.cs.geom.Point pt = new com.esri.mo2.cs.geom.Point();
  AcetateLayer acetLayer = new AcetateLayer(){
        public void paintComponent(java.awt.Graphics g) {
          if (pt != null) {
                java.awt.Graphics2D g2d = (java.awt.Graphics2D)g;
                g2d.setTransform(
                  map.getWorldToPixelTransform().toAffine());
                  g2d.setClip(map.getExtent());
          sms.draw(pt,g2d,"");
            }
          }
    };
  public DrawPoint () {
        sms.setType(SimpleMarkerSymbol.CIRCLE_MARKER);
        sms.setWidth(6);
        sms.setSymbolColor(Color.red);
        map.add(acetLayer);
  }
  public void mouseClicked(MouseEvent me) {
        pt = map.transformPixelToWorld(me.getX(),me.getY());
        acetLayer.repaint();
  }
    }

    class Flash extends Thread {

        Legend legend;

        Flash(Legend legendin) {
            legend = legendin;
        }

        public void run() {
            for (int i = 0; i < 12; i++) {
                try {
                    Thread.sleep(500);
                    legend.toggleSelected();
                } catch (Exception e) {
                }
            }
        }
    }
    
    class DistanceTool extends DragTool  {
  int startx,starty,endx,endy,currx,curry;
  com.esri.mo2.cs.geom.Point initPoint, endPoint, currPoint;
  double distance;
  public void mousePressed(MouseEvent me) {
        startx = me.getX(); starty = me.getY();
        initPoint = QuickStartfinal.map.transformPixelToWorld(me.getX(),me.getY());
  }
  public void mouseReleased(MouseEvent me) {
          // now we create an acetatelayer instance and draw a line on it
        endx = me.getX(); endy = me.getY();
        endPoint = QuickStartfinal.map.transformPixelToWorld(me.getX(),me.getY());
    distance = (69.44 / (2*Math.PI)) * 360 * Math.acos(
                                 Math.sin(initPoint.y * 2 * Math.PI / 360)
                           * Math.sin(endPoint.y * 2 * Math.PI / 360)
                           + Math.cos(initPoint.y * 2 * Math.PI / 360)
                           * Math.cos(endPoint.y * 2 * Math.PI / 360)
                           * (Math.abs(initPoint.x - endPoint.x) < 180 ?
                    Math.cos((initPoint.x - endPoint.x)*2*Math.PI/360):
                    Math.cos((360 - Math.abs(initPoint.x -
endPoint.x))*2*Math.PI/360)));
    System.out.println( distance  );
    QuickStartfinal.milesLabel.setText("DIST: " + new
Float((float)distance).toString() + " mi  ");
    QuickStartfinal.kmLabel.setText(new Float((float)(distance*1.6093)).toString() +
" km");
    if (QuickStartfinal.acetLayer != null)
      QuickStartfinal.map.remove(QuickStartfinal.acetLayer);
    QuickStartfinal.acetLayer = new AcetateLayer() {
      public void paintComponent(java.awt.Graphics g) {
                java.awt.Graphics2D g2d = (java.awt.Graphics2D) g;
                Line2D.Double line = new Line2D.Double(startx,starty,endx,endy);
                g2d.setColor(new Color(0,0,250));
                g2d.draw(line);
      }
    };
    Graphics g = super.getGraphics();
    QuickStartfinal.map.add(QuickStartfinal.acetLayer);
    QuickStartfinal.map.redraw();
  }
  
  public void cancel() {};
    }

class Hotlink extends Tool{
   public void mouseClicked(MouseEvent me){
      System.out.println("alllll");
     
     Map map=QuickStartfinal.map;
     com.esri.mo2.cs.geom.Point Point=map.transformPixelToWorld(me.getX(),me.getY());

     double gym[][]={{-117.0189931,32.6365829},{-116.961005,32.7461598},{-117.0104081,32.7814988},{-117.236413,33.081591},
         {-116.9637868,32.7960684} ,{-117.282535,33.034822},{-117.096435,33.113678},{-117.066079,33.06845},
         {-117.273819,32.843204},{-117.1617976,32.7127692},{-117.1598374,32.7492634},
         {-117.1578357,32.820656},{-117.1038614,32.7034152},{-117.056269,32.738011},{-117.3298645,33.12145056},
         {-117.076741,33.02458},{-117.11205,32.959892}};
     for(int i=0;i<18;i++){
     if(Math.abs(gym[i][0]-Point.getX())<0.02&&Math.abs(gym[i][1]-Point.getY())<0.02){

         try {
                HotPick hotpick = new HotPick(i);//opens dialog window with Duke in it
                hotpick.setVisible(true);
          } catch(Exception e){}
     }
     }
   }

} 
class HotPick extends JDialog {
     int pick=QuickStartfinal.mypick;
   
  HotPick(int j) throws IOException {

    setBounds(100,100,500,500);

    String image[][]={{"C:\\esri\\MOJ20\\examples\\24\\image1.jpg","Sport club","https://www.24hourfitness.com/Website/Club/71"},
        {"C:\\esri\\MOJ20\\examples\\24\\image2.jpg","Super club","https://www.24hourfitness.com/Website/Club/60"},
        {"C:\\esri\\MOJ20\\examples\\24\\image3.jpg","Sport club","http://www.24hourfitness.com/Website/Club/101"},
        { "C:\\esri\\MOJ20\\examples\\24\\image4.jpg","Super club","https://www.24hourfitness.com/Website/Club/927"},
        {"C:\\esri\\MOJ20\\examples\\24\\image5.jpg","Super club","http://www.24hourfitness.com/Website/Club/00880"},
        {"C:\\esri\\MOJ20\\examples\\24\\image6.jpg","Sport club","http://www.24hourfitness.com/Website/Club/00114"},
        {"C:\\esri\\MOJ20\\examples\\24\\image7.jpg","Super club","http://www.24hourfitness.com/Website/Club/95"},
        {"C:\\esri\\MOJ20\\examples\\24\\image8.jpg","Super club","http://www.24hourfitness.com/Website/Club/873"},
        {"C:\\esri\\MOJ20\\examples\\24\\image9.jpg","Sport club","http://www.24hourfitness.com/Website/Club/167"},
        {"C:\\esri\\MOJ20\\examples\\24\\image10.jpg","Sport club","http://www.24hourfitness.com/Website/Club/59"},
        {"C:\\esri\\MOJ20\\examples\\24\\image11.jpg","Sport club","http://www.24hourfitness.com/Website/Club/90"},
        {"C:\\esri\\MOJ20\\examples\\24\\image12.jpg","Super club","https://www.24hourfitness.com/Website/Club/892"},
        {"C:\\esri\\MOJ20\\examples\\24\\image13.jpg","Super club","http://www.24hourfitness.com/Website/Club/872"},
            {"C:\\esri\\MOJ20\\examples\\24\\image14.jpg","Sport club","http://www.24hourfitness.com/Website/Club/57"},
            {"C:\\esri\\MOJ20\\examples\\24\\image15.jpg","Sport club","https://www.24hourfitness.com/Website/Club/00888"},
            {"C:\\esri\\MOJ20\\examples\\24\\image16.jpg","Super club","http://www.24hourfitness.com/Website/Club/00160"},
            {"C:\\esri\\MOJ20\\examples\\24\\image17.jpg","Super club","http://www.24hourfitness.com/Website/Club/00191"}
        };
    setTitle(image[j][1]);
    JPanel jpanel = new JPanel();
    JPanel jpanel2 = new JPanel();
    JLabel label = new JLabel(new ImageIcon(image[j][0]));

     JLabel label2 = new JLabel(image[j][2]);
     jpanel2.add(label);

    jpanel.add(label2);
    getContentPane().add(jpanel2,BorderLayout.CENTER);
    getContentPane().add(jpanel,BorderLayout.SOUTH);
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
            setVisible(false);
          }
    });
  }
    }
}
