/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.winapi.gdi32
 * File name: Gdi32DLL.java
 * Created date: Aug 7, 2015
 * Description:
 */
package v2.org.analysis.apihandle.winapi.gdi32;

import v2.org.analysis.apihandle.structures.ABC;
import v2.org.analysis.apihandle.structures.ABCFLOAT;
import v2.org.analysis.apihandle.structures.COLORADJUSTMENT;
import v2.org.analysis.apihandle.structures.DEVMODE;
import v2.org.analysis.apihandle.structures.DOCINFO;
import v2.org.analysis.apihandle.structures.ENHMETAHEADER;
import v2.org.analysis.apihandle.structures.ENHMETARECORD;
import v2.org.analysis.apihandle.structures.ENUMLOGFONTEXDV;
import v2.org.analysis.apihandle.structures.GCP_RESULTS;
import v2.org.analysis.apihandle.structures.GLYPHSET;
import v2.org.analysis.apihandle.structures.HANDLETABLE;
import v2.org.analysis.apihandle.structures.KERNINGPAIR;
import v2.org.analysis.apihandle.structures.LOGBRUSH;
import v2.org.analysis.apihandle.structures.LOGFONT;
import v2.org.analysis.apihandle.structures.LOGPEN;
import v2.org.analysis.apihandle.structures.METAFILEPICT;
import v2.org.analysis.apihandle.structures.METARECORD;
import v2.org.analysis.apihandle.structures.OUTLINETEXTMETRIC;
import v2.org.analysis.apihandle.structures.POLYTEXT;
import v2.org.analysis.apihandle.structures.RASTERIZER_STATUS;
import v2.org.analysis.apihandle.structures.TEXTMETRIC;
import v2.org.analysis.apihandle.structures.XFORM;
import v2.org.analysis.apihandle.winapi.structures.BITMAP;
import v2.org.analysis.apihandle.winapi.structures.Wingdi.LOGPALETTE;
import v2.org.analysis.apihandle.winapi.structures.Wingdi.PALETTEENTRY;

import com.sun.jna.Native;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.HBITMAP;
import com.sun.jna.platform.win32.WinDef.HBRUSH;
import com.sun.jna.platform.win32.WinDef.HDC;
import com.sun.jna.platform.win32.WinDef.HPALETTE;
import com.sun.jna.platform.win32.WinDef.HRGN;
import com.sun.jna.platform.win32.WinDef.POINT;
import com.sun.jna.platform.win32.WinDef.RECT;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.platform.win32.WinGDI.PIXELFORMATDESCRIPTOR;
import com.sun.jna.platform.win32.WinGDI.RGBQUAD;
import com.sun.jna.platform.win32.WinGDI.RGNDATA;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.platform.win32.WinUser.SIZE;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.FloatByReference;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.ShortByReference;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

/**
 * @author Yen Nguyen
 *
 */
public interface Gdi32DLL extends StdCallLibrary {

	Gdi32DLL INSTANCE = (Gdi32DLL) Native.loadLibrary("gdi32", Gdi32DLL.class, W32APIOptions.DEFAULT_OPTIONS);
	Gdi32DLL SYNC_INSTANCE = (Gdi32DLL) Native.synchronizedLibrary(INSTANCE);

	/**
	 * The AnimatePalette function replaces entries in the specified logical
	 * palette.
	 * 
	 * @param hpal
	 *            A handle to the logical palette.
	 * 
	 * @param iStartIndex
	 *            The first logical palette entry to be replaced.
	 * 
	 * @param cEntries
	 *            The number of entries to be replaced.
	 * 
	 * @param pe
	 *            A pointer to the first member in an array of PALETTEENTRY
	 *            structures used to replace the current entries.
	 * 
	 * @return If the function succeeds, the return value is nonzero. If the
	 *         function fails, the return value is zero.
	 */
	int AnimatePalette(
	/* _In_ */HANDLE hpal,
	/* _In_ */UINT iStartIndex,
	/* _In_ */UINT cEntries,
	/* _In_ */PALETTEENTRY pe);

	/**
	 * The AddFontResource function adds the font resource from the specified
	 * file to the system font table. The font can subsequently be used for text
	 * output by any application.
	 * 
	 * @param lpszFilename
	 *            A pointer to a null-terminated character string that contains
	 *            a valid font file name. This parameter can specify any of the
	 *            following files.
	 * 
	 * @return If the function succeeds, the return value specifies the number
	 *         of fonts added. If the function fails, the return value is zero.
	 *         No extended error information is available.
	 */
	int AddFontResource(/* _In_ */WString lpszFilename);

	/**
	 * The GetStockObject function retrieves a handle to one of the stock pens,
	 * brushes, fonts, or palettes.
	 * 
	 * @param fnObject
	 *            The type of stock object. This parameter can be one of the
	 *            following values.
	 * 
	 * @return If the function succeeds, the return value is a handle to the
	 *         requested logical object. If the function fails, the return value
	 *         is NULL.
	 */
	HANDLE GetStockObject(/* _In_ */int fnObject);

	/**
	 * The GdiGetBatchLimit function returns the maximum number of function
	 * calls that can be accumulated in the calling thread's current batch. The
	 * system flushes the current batch whenever this limit is exceeded.
	 * 
	 * @return If the function succeeds, the return value is the batch limit. If
	 *         the function fails, the return value is zero.
	 */
	DWORD GdiGetBatchLimit();

	/**
	 * The GetBkColor function returns the current background color for the
	 * specified device context.
	 * 
	 * @param hdc
	 *            Handle to the device context whose background color is to be
	 *            returned.
	 * 
	 * @return If the function succeeds, the return value is a COLORREF value
	 *         for the current background color. If the function fails, the
	 *         return value is CLR_INVALID.
	 */
	DWORD GetBkColor(HDC hdc);

	/**
	 * The SetBkColor function sets the current background color to the
	 * specified color value, or to the nearest physical color if the device
	 * cannot represent the specified color value.
	 * 
	 * @param hdc
	 *            A handle to the device context.
	 * 
	 * @param crColor
	 *            The new background color. To make a COLORREF value, use the
	 *            RGB macro.
	 * 
	 * @return If the function succeeds, the return value specifies the previous
	 *         background color as a COLORREF value. If the function fails, the
	 *         return value is CLR_INVALID.
	 */
	DWORD SetBkColor(
	/* _In_ */HDC hdc,
	/* _In_ */DWORD crColor);

	/**
	 * The CreateCompatibleDC function creates a memory device context (DC)
	 * compatible with the specified device.
	 * 
	 * @param hdc
	 *            A handle to an existing DC. If this handle is NULL, the
	 *            function creates a memory DC compatible with the application's
	 *            current screen.
	 * 
	 * @return If the function succeeds, the return value is the handle to a
	 *         memory DC. If the function fails, the return value is NULL.
	 */
	HDC CreateCompatibleDC(/* _In_ */HDC hdc);

	/**
	 * The CreatePalette function creates a logical palette.
	 * 
	 * @param lplgpl
	 *            A pointer to a LOGPALETTE structure that contains information
	 *            about the colors in the logical palette.
	 * 
	 * @return If the function succeeds, the return value is a handle to a
	 *         logical palette. If the function fails, the return value is NULL.
	 */
	HANDLE CreatePalette(/* _In_ *//* const */LOGPALETTE lplgpl);

	/**
	 * The DeleteObject function deletes a logical pen, brush, font, bitmap,
	 * region, or palette, freeing all system resources associated with the
	 * object. After the object is deleted, the specified handle is no longer
	 * valid.
	 * 
	 * @param hObject
	 *            A handle to a logical pen, brush, font, bitmap, region, or
	 *            palette.
	 * 
	 * @return If the function succeeds, the return value is nonzero. If the
	 *         specified handle is not valid or is currently selected into a DC,
	 *         the return value is zero.
	 */
	BOOL DeleteObject(HANDLE hObject);

	/**
	 * The SetTextAlign function sets the text-alignment flags for the specified
	 * device context.
	 * 
	 * @param hdc
	 *            A handle to the device context.
	 * 
	 * @param fMode
	 *            The text alignment by using a mask of the values in the
	 *            following list. Only one flag can be chosen from those that
	 *            affect horizontal and vertical alignment. In addition, only
	 *            one of the two flags that alter the current position can be
	 *            chosen.
	 * 
	 * @return If the function succeeds, the return value is the previous
	 *         text-alignment setting. If the function fails, the return value
	 *         is GDI_ERROR.
	 */
	UINT SetTextAlign(/* _In_ */HDC hdc,/* _In_ */UINT fMode);

	/**
	 * The StrokePath function renders the specified path by using the current
	 * pen.
	 * 
	 * @param hdc
	 *            Handle to a device context that contains the completed path.
	 * 
	 * @return If the function succeeds, the return value is nonzero. If the
	 *         function fails, the return value is zero.
	 */
	BOOL StrokePath( /* _In_ */HDC hdc);

	/**
	 * The StrokeAndFillPath function closes any open figures in a path, strokes
	 * the outline of the path by using the current pen, and fills its interior
	 * by using the current brush.
	 * 
	 * @param hdc
	 *            A handle to the device context.
	 * 
	 * @return If the function succeeds, the return value is nonzero. If the
	 *         function fails, the return value is zero.
	 */
	BOOL StrokeAndFillPath(/* _In_ */HDC hdc);

	/**
	 * The DPtoLP function converts device coordinates into logical coordinates.
	 * The conversion depends on the mapping mode of the device context, the
	 * settings of the origins and extents for the window and viewport, and the
	 * world transformation.
	 * 
	 * @param hdc
	 *            A handle to the device context.
	 * 
	 * @param lpPoints
	 *            A pointer to an array of POINT structures. The x- and
	 *            y-coordinates contained in each POINT structure will be
	 *            transformed.
	 * 
	 * @param nCount
	 *            The number of points in the array.
	 * 
	 * @return If the function succeeds, the return value is nonzero. If the
	 *         function fails, the return value is zero.
	 */
	BOOL DPtoLP(
	/* _In_ */HDC hdc,
	/* _Inout_ */POINT lpPoints,
	/* _In_ */int nCount);

	// int EnumFontFamiliesEx(
	// /*_In_*/ HDC hdc,
	// /*_In_*/ LOGFONT lpLogfont,
	// /*_In_*/ FONTENUMPROC lpEnumFontFamExProc,
	// /*_In_*/ LPARAM lParam,
	// DWORD dwFlags
	// );
	
	// API's Interfaces
		int AbortDoc ( HDC hdc );

		int AbortPath ( HDC hdc );

		int AddFontResource ( String lpszFilename );

		int AngleArc ( HDC hdc, int X, int Y, DWORD dwRadius, float eStartAngle, float eSweepAngle );

//		int AnimatePalette ( HPALETTE hpal, UINT iStartIndex, UINT cEntries, PALETTEENTRY ppe );

		int Arc ( HDC hdc, int nLeftRect, int nTopRect, int nRightRect, int nBottomRect, int nXStartArc, int nYStartArc, int nXEndArc, int nYEndArc );

		int ArcTo ( HDC hdc, int nLeftRect, int nTopRect, int nRightRect, int nBottomRect, int nXRadial1, int nYRadial1, int nXRadial2, int nYRadial2 );

		int BeginPath ( HDC hdc );

		int BitBlt ( HDC hdcDest, int nXDest, int nYDest, int nWidth, int nHeight, HDC hdcSrc, int nXSrc, int nYSrc, DWORD dwRop );

		int CancelDC ( HDC hdc );

		int ChoosePixelFormat ( HDC hdc, PIXELFORMATDESCRIPTOR ppfd );

		int Chord ( HDC hdc, int nLeftRect, int nTopRect, int nRightRect, int nBottomRect, int nXRadial1, int nYRadial1, int nXRadial2, int nYRadial2 );

		int CloseEnhMetaFile ( HDC hdc );

		int CloseFigure ( HDC hdc );

		int CloseMetaFile ( HDC hdc );

		int CombineRgn ( HRGN hrgnDest, HRGN hrgnSrc1, HRGN hrgnSrc2, int fnCombineMode );

		int CombineTransform ( XFORM lpxformResult, XFORM lpxform1, XFORM lpxform2 );

		int CopyEnhMetaFile ( HANDLE hemfSrc, String lpszFile );

		int CopyMetaFile ( HANDLE hmfSrc, String lpszFile );

		int CreateBitmapIndirect ( BITMAP lpbm );

		int CreateBrushIndirect ( LOGBRUSH lplb );

		int CreateCompatibleBitmap ( HDC hdc, int nWidth, int nHeight );

//		int CreateCompatibleDC ( HDC hdc );

		int CreateDC ( String lpszDriver, String lpszDevice, String lpszOutput, DEVMODE lpInitData );

		int CreateDIBPatternBrush ( HANDLE hglbDIBPacked, UINT fuColorSpec );

		int CreateDiscardableBitmap ( HDC hdc, int nWidth, int nHeight );

		int CreateEllipticRgn ( int nLeftRect, int nTopRect, int nRightRect, int nBottomRect );

		int CreateEllipticRgnIndirect ( RECT lprc );

		int CreateEnhMetaFile ( HDC hdcRef, String lpFilename, RECT lpRect, String lpDescription );

		int CreateFont ( int nHeight, int nWidth, int nEscapement, int nOrientation, int fnWeight, DWORD fdwItalic, DWORD fdwUnderline, DWORD fdwStrikeOut, DWORD fdwCharSet, DWORD fdwOutputPrecision, DWORD fdwClipPrecision, DWORD fdwQuality, DWORD fdwPitchAndFamily, String lpszFace );

		int CreateFontIndirect ( LOGFONT lplf );

		int CreateFontIndirectEx ( ENUMLOGFONTEXDV penumlfex );

		int CreateHalftonePalette ( HDC hdc );

		int CreateHatchBrush ( int fnStyle, int clrref );

		int CreateIC ( String lpszDriver, String lpszDevice, String lpszOutput, DEVMODE lpdvmInit );

		int CreateMetaFile ( String lpszFile );

//		int CreatePalette ( LOGPALETTE lplgpl );

		int CreatePatternBrush ( HBITMAP hbmp );

		int CreatePen ( int fnPenStyle, int nWidth, int crColor );

		int CreatePenIndirect ( LOGPEN lplgpn );

		int CreatePolygonRgn ( POINT lppt, int cPoints, int fnPolyFillMode );

		int CreatePolyPolygonRgn ( POINT lppt, int[] lpPolyCounts, int nCount, int fnPolyFillMode );

		int CreateRectRgn ( int nLeftRect, int nTopRect, int nRightRect, int nBottomRect );

		int CreateRectRgnIndirect ( RECT lprc );

		int CreateRoundRectRgn ( int nLeftRect, int nTopRect, int nRightRect, int nBottomRect, int nWidthEllipse, int nHeightEllipse );

		int CreateScalableFontResource ( DWORD fdwHidden, String lpszFontRes, String lpszFontFile, String lpszCurrentPath );

		int CreateSolidBrush ( int crColor );

		int DeleteDC ( HDC hdc );

		int DeleteEnhMetaFile ( HANDLE hemf );

		int DeleteMetaFile ( HANDLE hmf );

//		int DeleteObject ( HANDLE hObject );

//		int DPtoLP ( HDC hdc, POINT lpPoints, int nCount );

		int DrawEscape ( HDC hdc, int nEscape, int cbInput, ByteByReference lpszInData );

		int Ellipse ( HDC hdc, int nLeftRect, int nTopRect, int nRightRect, int nBottomRect );

		int EndDoc ( HDC hdc );

		int EndPage ( HDC hdc );

		int EndPath ( HDC hdc );

		int EqualRgn ( HRGN hSrcRgn1, HRGN hSrcRgn2 );

		int ExcludeClipRect ( HDC hdc, int nLeftRect, int nTopRect, int nRightRect, int nBottomRect );

		int ExtCreatePen ( DWORD dwPenStyle, DWORD dwWidth, LOGBRUSH lplb, DWORD dwStyleCount, IntByReference lpStyle );

		int ExtCreateRegion ( XFORM lpXform, DWORD nCount, RGNDATA lpRgnData );

		int ExtEscape ( HDC hdc, int nEscape, int cbInput, ByteByReference lpszInData, int cbOutput, ByteByReference lpszOutData );

		int ExtFloodFill ( HDC hdc, int nXStart, int nYStart, int crColor, UINT fuFillType );

		int ExtSelectClipRgn ( HDC hdc, HRGN hrgn, int fnMode );

		int ExtTextOut ( HDC hdc, int X, int Y, UINT fuOptions, RECT lprc, String lpString, UINT cbCount, IntByReference lpDx );

		int FillPath ( HDC hdc );

		int FillRgn ( HDC hdc, HRGN hrgn, HBRUSH hbr );

		int FlattenPath ( HDC hdc );

		int FloodFill ( HDC hdc, int nXStart, int nYStart, int crFill );

		int FrameRgn ( HDC hdc, HRGN hrgn, HBRUSH hbr, int nWidth, int nHeight );

		int GdiComment ( HDC hdc, UINT cbSize, byte[] lpData );

		int GdiFlush ( );

//		int GdiGetBatchLimit ( );

		int GdiSetBatchLimit ( DWORD dwLimit );

		int GetArcDirection ( HDC hdc );

		int GetAspectRatioFilterEx ( HDC hdc, SIZE lpAspectRatio );

		int GetBitmapDimensionEx ( HBITMAP hBitmap, SIZE lpDimension );

//		int GetBkColor ( HDC hdc );

		int GetBkMode ( HDC hdc );

		int GetBoundsRect ( HDC hdc, RECT lprcBounds, UINT flags );

		int GetBrushOrgEx ( HDC hdc, POINT lppt );

		int GetCharABCWidths ( HDC hdc, UINT uFirstChar, UINT uLastChar, ABC lpabc );

		int GetCharABCWidthsFloat ( HDC hdc, UINT iFirstChar, UINT iLastChar, ABCFLOAT lpABCF );

		int GetCharABCWidthsI ( HDC hdc, UINT giFirst, UINT cgi, ShortByReference pgi, ABC lpabc );

		int GetCharacterPlacement ( HDC hdc, String lpString, int nCount, int nMaxExtent, GCP_RESULTS lpResults, DWORD dwFlags );

		int GetCharWidth ( HDC hdc, UINT iFirstChar, UINT iLastChar, IntByReference lpBuffer );

		int GetCharWidth32 ( HDC hdc, UINT iFirstChar, UINT iLastChar, IntByReference lpBuffer );

		int GetCharWidthFloat ( HDC hdc, UINT iFirstChar, UINT iLastChar, FloatByReference pxBuffer );

		int GetCharWidthI ( HDC hdc, UINT giFirst, UINT cgi, ShortByReference pgi, IntByReference lpBuffer );

		int GetClipBox ( HDC hdc, RECT lprc );

		int GetClipRgn ( HDC hdc, HRGN hrgn );

		int GetColorAdjustment ( HDC hdc, COLORADJUSTMENT lpca );

		int GetCurrentObject ( HDC hdc, UINT uObjectType );

		int GetCurrentPositionEx ( HDC hdc, POINT lpPoint );

		int GetDCBrushColor ( HDC hdc );

		int GetDCOrgEx ( HDC hdc, POINT lpPoint );

		int GetDCPenColor ( HDC hdc );

		int GetDeviceCaps ( HDC hdc, int nIndex );

		int GetDIBColorTable ( HDC hdc, UINT uStartIndex, UINT cEntries, RGBQUAD pColors );

		int GetEnhMetaFile ( String lpszMetaFile );

		int GetEnhMetaFileBits ( HANDLE hemf, UINT cbBuffer, byte lpbBuffer );

		int GetEnhMetaFileDescription ( HANDLE hemf, UINT cchBuffer, String lpszDescription );

		int GetEnhMetaFileHeader ( HANDLE hemf, UINT cbBuffer, ENHMETAHEADER lpemh );

		int GetEnhMetaFilePaletteEntries ( HANDLE hemf, UINT cEntries, v2.org.analysis.apihandle.structures.PALETTEENTRY lppe );

		int GetFontLanguageInfo ( HDC hdc );

		int GetFontUnicodeRanges ( HDC hdc, GLYPHSET lpgs );

		int GetGlyphIndices ( HDC hdc, String lpstr, int c, ShortByReference pgi, DWORD fl );

		int GetGraphicsMode ( HDC hdc );

		int GetKerningPairs ( HDC hdc, DWORD nNumPairs, KERNINGPAIR lpkrnpair );

		int GetLayout ( HDC hdc );

		int GetMapMode ( HDC hdc );

		int GetMetaRgn ( HDC hdc, HRGN hrgn );

		int GetMiterLimit ( HDC hdc, FloatByReference peLimit );

		int GetNearestColor ( HDC hdc, int crColor );

		int GetNearestPaletteIndex ( HPALETTE hpal, int crColor );

		int GetObjectType ( HANDLE h );

		int GetOutlineTextMetrics ( HDC hdc, UINT cbData, OUTLINETEXTMETRIC lpOTM );

		int GetPaletteEntries ( HPALETTE hpal, UINT iStartIndex, UINT nEntries, v2.org.analysis.apihandle.structures.PALETTEENTRY lppe );

		int GetPath ( HDC hdc, POINT lpPoints, byte[] lpTypes, int nSize );

		int GetPixel ( HDC hdc, int nXPos, int nYPos );

		int GetPolyFillMode ( HDC hdc );

		int GetRandomRgn ( HDC hdc, HRGN hrgn, int iNum );

		int GetRasterizerCaps ( RASTERIZER_STATUS lprs, UINT cb );

		int GetRegionData ( HRGN hRgn, DWORD dwCount, RGNDATA lpRgnData );

		int GetRgnBox ( HRGN hrgn, RECT lprc );

		int GetROP2 ( HDC hdc );

//		int GetStockObject ( int fnObject );

		int GetStretchBltMode ( HDC hdc );

		int GetSystemPaletteEntries ( HDC hdc, UINT iStartIndex, UINT nEntries, v2.org.analysis.apihandle.structures.PALETTEENTRY lppe );

		int GetSystemPaletteUse ( HDC hdc );

		int GetTextAlign ( HDC hdc );

		int GetTextCharacterExtra ( HDC hdc );

		int GetTextColor ( HDC hdc );

		int GetTextExtentExPoint ( HDC hdc, String lpszStr, int cchString, int nMaxExtent, IntByReference lpnFit, int[] alpDx, SIZE lpSize );

		int GetTextExtentExPointI ( HDC hdc, short[] pgiIn, int cgi, int nMaxExtent, IntByReference lpnFit, int[] alpDx, SIZE lpSize );

		int GetTextExtentPoint ( HDC hdc, String lpString, int cbString, SIZE lpSize );

		int GetTextExtentPoint32 ( HDC hdc, char[] lpString, int c, SIZE lpSize );

		int GetTextExtentPointI ( HDC hdc, short[] pgiIn, int cgi, SIZE lpSize );

		int GetTextFace ( HDC hdc, int nCount, String lpFaceName );

		int GetTextMetrics ( HDC hdc, TEXTMETRIC lptm );

		int GetViewportExtEx ( HDC hdc, SIZE lpSize );

		int GetViewportOrgEx ( HDC hdc, POINT lpPoint );

		int GetWindowExtEx ( HDC hdc, SIZE lpSize );

		int GetWindowOrgEx ( HDC hdc, POINT lpPoint );

		int GetWinMetaFileBits ( HANDLE hemf, UINT cbBuffer, byte[] lpbBuffer, int fnMapMode, HDC hdcRef );

		int GetWorldTransform ( HDC hdc, XFORM lpXform );

		int IntersectClipRect ( HDC hdc, int nLeftRect, int nTopRect, int nRightRect, int nBottomRect );

		int InvertRgn ( HDC hdc, HRGN hrgn );

		int LineTo ( HDC hdc, int nXEnd, int nYEnd );

		int LPtoDP ( HDC hdc, POINT lpPoints, int nCount );

		int MaskBlt ( HDC hdcDest, int nXDest, int nYDest, int nWidth, int nHeight, HDC hdcSrc, int nXSrc, int nYSrc, HBITMAP hbmMask, int xMask, int yMask, DWORD dwRop );

		int ModifyWorldTransform ( HDC hdc, XFORM lpXform, DWORD iMode );

		int MoveToEx ( HDC hdc, int X, int Y, POINT lpPoint );

		int OffsetClipRgn ( HDC hdc, int nXOffset, int nYOffset );

		int OffsetRgn ( HRGN hrgn, int nXOffset, int nYOffset );

		int OffsetViewportOrgEx ( HDC hdc, int nXOffset, int nYOffset, POINT lpPoint );

		int OffsetWindowOrgEx ( HDC hdc, int nXOffset, int nYOffset, POINT lpPoint );

		int PaintRgn ( HDC hdc, HRGN hrgn );

		int PatBlt ( HDC hdc, int nXLeft, int nYLeft, int nWidth, int nHeight, DWORD dwRop );

		int PathToRegion ( HDC hdc );

		int Pie ( HDC hdc, int nLeftRect, int nTopRect, int nRightRect, int nBottomRect, int nXRadial1, int nYRadial1, int nXRadial2, int nYRadial2 );

		int PlayEnhMetaFile ( HDC hdc, HANDLE hemf, RECT lpRect );

		int PlayEnhMetaFileRecord ( HDC hdc, HANDLETABLE lpHandletable, ENHMETARECORD lpEnhMetaRecord, UINT nHandles );

		int PlayMetaFile ( HDC hdc, HANDLE hmf );

		int PlayMetaFileRecord ( HDC hdc, HANDLETABLE lpHandletable, METARECORD lpMetaRecord, UINT nHandles );

		int PlgBlt ( HDC hdcDest, POINT lpPoint, HDC hdcSrc, int nXSrc, int nYSrc, int nWidth, int nHeight, HBITMAP hbmMask, int xMask, int yMask );

		int PolyBezier ( HDC hdc, POINT lppt, DWORD cPoints );

		int PolyBezierTo ( HDC hdc, POINT lppt, DWORD cCount );

		int PolyDraw ( HDC hdc, POINT lppt, byte[] lpbTypes, int cCount );

		int Polygon ( HDC hdc, POINT lpPoints, int nCount );

		int Polyline ( HDC hdc, POINT lppt, int cPoints );

		int PolylineTo ( HDC hdc, POINT lppt, DWORD cCount );

		int PolyPolygon ( HDC hdc, POINT lpPoints, int[] lpPolyCounts, int nCount );

		int PolyPolyline ( HDC hdc, POINT lppt, int[] lpdwPolyPoints, DWORD cCount );

		int PolyTextOut ( HDC hdc, POLYTEXT pptxt, int cStrings );

		int PtInRegion ( HRGN hrgn, int X, int Y );

		int PtVisible ( HDC hdc, int X, int Y );

		int RealizePalette ( HDC hdc );

		int Rectangle ( HDC hdc, int nLeftRect, int nTopRect, int nRightRect, int nBottomRect );

		int RectInRegion ( HRGN hrgn, RECT lprc );

		int RectVisible ( HDC hdc, RECT lprc );

		int RemoveFontMemResourceEx ( HANDLE fh );

		int RemoveFontResource ( String lpFileName );

		int ResetDC ( HDC hdc, DEVMODE lpInitData );

		int ResizePalette ( HPALETTE hpal, UINT nEntries );

		int RestoreDC ( HDC hdc, int nSavedDC );

		int RoundRect ( HDC hdc, int nLeftRect, int nTopRect, int nRightRect, int nBottomRect, int nWidth, int nHeight );

		int SaveDC ( HDC hdc );

		int ScaleViewportExtEx ( HDC hdc, int Xnum, int Xdenom, int Ynum, int Ydenom, SIZE lpSize );

		int ScaleWindowExtEx ( HDC hdc, int Xnum, int Xdenom, int Ynum, int Ydenom, SIZE lpSize );

		int SelectClipPath ( HDC hdc, int iMode );

		int SelectClipRgn ( HDC hdc, HRGN hrgn );

		int SelectObject ( HDC hdc, HANDLE hgdiobj );

		int SelectPalette ( HDC hdc, HPALETTE hpal, BOOL bForceBackground );

		int SetArcDirection ( HDC hdc, int ArcDirection );

		int SetBitmapDimensionEx ( HBITMAP hBitmap, int nWidth, int nHeight, SIZE lpSize );

		int SetBkColor ( HDC hdc, int crColor );

		int SetBkMode ( HDC hdc, int iBkMode );

		int SetBoundsRect ( HDC hdc, RECT lprcBounds, UINT flags );

		int SetBrushOrgEx ( HDC hdc, int nXOrg, int nYOrg, POINT lppt );

		int SetColorAdjustment ( HDC hdc, COLORADJUSTMENT lpca );

		int SetDCBrushColor ( HDC hdc, int crColor );

		int SetDCPenColor ( HDC hdc, int crColor );

		int SetDIBColorTable ( HDC hdc, UINT uStartIndex, UINT cEntries, RGBQUAD pColors );

		int SetEnhMetaFileBits ( UINT cbBuffer, byte lpData );

		int SetGraphicsMode ( HDC hdc, int iMode );

		int SetLayout ( HDC hdc, DWORD dwLayout );

		int SetMapMode ( HDC hdc, int fnMapMode );

		int SetMapperFlags ( HDC hdc, DWORD dwFlag );

		int SetMetaFileBitsEx ( UINT nSize, byte lpData );

		int SetMetaRgn ( HDC hdc );

		int SetMiterLimit ( HDC hdc, float eNewLimit, FloatByReference peOldLimit );

		int SetPaletteEntries ( HPALETTE hpal, UINT iStart, UINT cEntries, v2.org.analysis.apihandle.structures.PALETTEENTRY lppe );

		int SetPixel ( HDC hdc, int X, int Y, int crColor );

		int SetPixelFormat ( HDC hdc, int iPixelFormat, PIXELFORMATDESCRIPTOR ppfd );

		int SetPixelV ( HDC hdc, int X, int Y, int crColor );

		int SetPolyFillMode ( HDC hdc, int iPolyFillMode );

		int SetRectRgn ( HRGN hrgn, int nLeftRect, int nTopRect, int nRightRect, int nBottomRect );

		int SetROP2 ( HDC hdc, int fnDrawMode );

		int SetStretchBltMode ( HDC hdc, int iStretchMode );

		int SetSystemPaletteUse ( HDC hdc, UINT uUsage );

//		int SetTextAlign ( HDC hdc, UINT fMode );

		int SetTextCharacterExtra ( HDC hdc, int nCharExtra );

		int SetTextColor ( HDC hdc, int crColor );

		int SetTextJustification ( HDC hdc, int nBreakExtra, int nBreakCount );

		int SetViewportExtEx ( HDC hdc, int nXExtent, int nYExtent, SIZE lpSize );

		int SetViewportOrgEx ( HDC hdc, int X, int Y, POINT lpPoint );

		int SetWindowExtEx ( HDC hdc, int nXExtent, int nYExtent, SIZE lpSize );

		int SetWindowOrgEx ( HDC hdc, int X, int Y, POINT lpPoint );

		int SetWinMetaFileBits ( UINT cbBuffer, byte[] lpbBuffer, HDC hdcRef, METAFILEPICT lpmfp );

		int SetWorldTransform ( HDC hdc, XFORM lpXform );

		int StartDoc ( HDC hdc, DOCINFO lpdi );

		int StartPage ( HDC hDC );

		int StretchBlt ( HDC hdcDest, int nXOriginDest, int nYOriginDest, int nWidthDest, int nHeightDest, HDC hdcSrc, int nXOriginSrc, int nYOriginSrc, int nWidthSrc, int nHeightSrc, DWORD dwRop );

//		int StrokeAndFillPath ( HDC hdc );

//		int StrokePath ( HDC hdc );

		int TextOut ( HDC hdc, int nXStart, int nYStart, String lpString, int cchString );

		int UnrealizeObject ( HANDLE hgdiobj );

		int UpdateColors ( HDC hdc );

		int WidenPath ( HDC hdc );
}
