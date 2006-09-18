/*
 * Copyright (c) 2002-2004 LWJGL Project
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'LWJGL' nor the names of
 *   its contributors may be used to endorse or promote products derived
 *   from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.lwjgl.opengl;

/**
 * @author elias_naur
 */

import org.lwjgl.input.Keyboard;

final class LinuxKeycodes {
	public final static int XK_Kanji                         = 0xff21;

	public final static int XK_ISO_Left_Tab                  = 0xfe20;
	
	public final static int XK_dead_grave                    = 0xfe50;
	public final static int XK_dead_acute                    = 0xfe51;
	public final static int XK_dead_circumflex               = 0xfe52;
	public final static int XK_dead_tilde                    = 0xfe53;
	public final static int XK_dead_macron                   = 0xfe54;
	public final static int XK_dead_breve                    = 0xfe55;
	public final static int XK_dead_abovedot                 = 0xfe56;
	public final static int XK_dead_diaeresis                = 0xfe57;
	public final static int XK_dead_abovering                = 0xfe58;
	public final static int XK_dead_doubleacute              = 0xfe59;
	public final static int XK_dead_caron                    = 0xfe5a;
	public final static int XK_dead_cedilla                  = 0xfe5b;
	public final static int XK_dead_ogonek                   = 0xfe5c;
	public final static int XK_dead_iota                     = 0xfe5d;
	public final static int XK_dead_voiced_sound             = 0xfe5e;
	public final static int XK_dead_semivoiced_sound         = 0xfe5f;
	public final static int XK_dead_belowdot                 = 0xfe60;
	public final static int XK_dead_hook                     = 0xfe61;
	public final static int XK_dead_horn                     = 0xfe62;

	public final static int XK_BackSpace                     = 0xff08;
	public final static int XK_Tab                           = 0xff09;
	public final static int XK_Linefeed                      = 0xff0a;
	public final static int XK_Clear                         = 0xff0b;
	public final static int XK_Return                        = 0xff0d;
	public final static int XK_Pause                         = 0xff13;
	public final static int XK_Scroll_Lock                   = 0xff14;
	public final static int XK_Sys_Req                       = 0xff15;
	public final static int XK_Escape                        = 0xff1b;
	public final static int XK_Delete                        = 0xffff;

	public final static int XK_Home                          = 0xff50;
	public final static int XK_Left                          = 0xff51;
	public final static int XK_Up                            = 0xff52;
	public final static int XK_Right                         = 0xff53;
	public final static int XK_Down                          = 0xff54;
	public final static int XK_Prior                         = 0xff55;
	public final static int XK_Page_Up                       = 0xff55;
	public final static int XK_Next                          = 0xff56;
	public final static int XK_Page_Down                     = 0xff56;
	public final static int XK_End                           = 0xff57;
	public final static int XK_Begin                         = 0xff58;


/* Misc functions */

	public final static int XK_Select                        = 0xff60;
	public final static int XK_Print                         = 0xff61;
	public final static int XK_Execute                       = 0xff62;
	public final static int XK_Insert                        = 0xff63;
	public final static int XK_Undo                          = 0xff65;
	public final static int XK_Redo                          = 0xff66;
	public final static int XK_Menu                          = 0xff67;
	public final static int XK_Find                          = 0xff68;
	public final static int XK_Cancel                        = 0xff69;
	public final static int XK_Help                          = 0xff6a;
	public final static int XK_Break                         = 0xff6b;
	public final static int XK_Mode_switch                   = 0xff7e;
	public final static int XK_script_switch                 = 0xff7e;
	public final static int XK_Num_Lock                      = 0xff7f;

/* Keypad functions, keypad numbers cleverly chosen to map to ASCII */

	public final static int XK_KP_Space                      = 0xff80;
	public final static int XK_KP_Tab                        = 0xff89;
	public final static int XK_KP_Enter                      = 0xff8d;
	public final static int XK_KP_F1                         = 0xff91;
	public final static int XK_KP_F2                         = 0xff92;
	public final static int XK_KP_F3                         = 0xff93;
	public final static int XK_KP_F4                         = 0xff94;
	public final static int XK_KP_Home                       = 0xff95;
	public final static int XK_KP_Left                       = 0xff96;
	public final static int XK_KP_Up                         = 0xff97;
	public final static int XK_KP_Right                      = 0xff98;
	public final static int XK_KP_Down                       = 0xff99;
	public final static int XK_KP_Prior                      = 0xff9a;
	public final static int XK_KP_Page_Up                    = 0xff9a;
	public final static int XK_KP_Next                       = 0xff9b;
	public final static int XK_KP_Page_Down                  = 0xff9b;
	public final static int XK_KP_End                        = 0xff9c;
	public final static int XK_KP_Begin                      = 0xff9d;
	public final static int XK_KP_Insert                     = 0xff9e;
	public final static int XK_KP_Delete                     = 0xff9f;
	public final static int XK_KP_Equal                      = 0xffbd;
	public final static int XK_KP_Multiply                   = 0xffaa;
	public final static int XK_KP_Add                        = 0xffab;
	public final static int XK_KP_Separator                  = 0xffac;
	public final static int XK_KP_Subtract                   = 0xffad;
	public final static int XK_KP_Decimal                    = 0xffae;
	public final static int XK_KP_Divide                     = 0xffaf;

	public final static int XK_KP_0                          = 0xffb0;
	public final static int XK_KP_1                          = 0xffb1;
	public final static int XK_KP_2                          = 0xffb2;
	public final static int XK_KP_3                          = 0xffb3;
	public final static int XK_KP_4                          = 0xffb4;
	public final static int XK_KP_5                          = 0xffb5;
	public final static int XK_KP_6                          = 0xffb6;
	public final static int XK_KP_7                          = 0xffb7;
	public final static int XK_KP_8                          = 0xffb8;
	public final static int XK_KP_9                          = 0xffb9;



/*
 * Auxilliary functions; note the duplicate definitions for left and right
 * function keys;  Sun keyboards and a few other manufactures have such
 * function key groups on the left and/or right sides of the keyboard.
 * We've not found a keyboard with more than 35 function keys total.
 */

	public final static int XK_F1                            = 0xffbe;
	public final static int XK_F2                            = 0xffbf;
	public final static int XK_F3                            = 0xffc0;
	public final static int XK_F4                            = 0xffc1;
	public final static int XK_F5                            = 0xffc2;
	public final static int XK_F6                            = 0xffc3;
	public final static int XK_F7                            = 0xffc4;
	public final static int XK_F8                            = 0xffc5;
	public final static int XK_F9                            = 0xffc6;
	public final static int XK_F10                           = 0xffc7;
	public final static int XK_F11                           = 0xffc8;
	public final static int XK_L1                            = 0xffc8;
	public final static int XK_F12                           = 0xffc9;
	public final static int XK_L2                            = 0xffc9;
	public final static int XK_F13                           = 0xffca;
	public final static int XK_L3                            = 0xffca;
	public final static int XK_F14                           = 0xffcb;
	public final static int XK_L4                            = 0xffcb;
	public final static int XK_F15                           = 0xffcc;
	public final static int XK_L5                            = 0xffcc;
	public final static int XK_F16                           = 0xffcd;
	public final static int XK_L6                            = 0xffcd;
	public final static int XK_F17                           = 0xffce;
	public final static int XK_L7                            = 0xffce;
	public final static int XK_F18                           = 0xffcf;
	public final static int XK_L8                            = 0xffcf;
	public final static int XK_F19                           = 0xffd0;
	public final static int XK_L9                            = 0xffd0;
	public final static int XK_F20                           = 0xffd1;
	public final static int XK_L10                           = 0xffd1;
	public final static int XK_F21                           = 0xffd2;
	public final static int XK_R1                            = 0xffd2;
	public final static int XK_F22                           = 0xffd3;
	public final static int XK_R2                            = 0xffd3;
	public final static int XK_F23                           = 0xffd4;
	public final static int XK_R3                            = 0xffd4;
	public final static int XK_F24                           = 0xffd5;
	public final static int XK_R4                            = 0xffd5;
	public final static int XK_F25                           = 0xffd6;
	public final static int XK_R5                            = 0xffd6;
	public final static int XK_F26                           = 0xffd7;
	public final static int XK_R6                            = 0xffd7;
	public final static int XK_F27                           = 0xffd8;
	public final static int XK_R7                            = 0xffd8;
	public final static int XK_F28                           = 0xffd9;
	public final static int XK_R8                            = 0xffd9;
	public final static int XK_F29                           = 0xffda;
	public final static int XK_R9                            = 0xffda;
	public final static int XK_F30                           = 0xffdb;
	public final static int XK_R10                           = 0xffdb;
	public final static int XK_F31                           = 0xffdc;
	public final static int XK_R11                           = 0xffdc;
	public final static int XK_F32                           = 0xffdd;
	public final static int XK_R12                           = 0xffdd;
	public final static int XK_F33                           = 0xffde;
	public final static int XK_R13                           = 0xffde;
	public final static int XK_F34                           = 0xffdf;
	public final static int XK_R14                           = 0xffdf;
	public final static int XK_F35                           = 0xffe0;
	public final static int XK_R15                           = 0xffe0;

/* Modifiers */

	public final static int XK_Shift_L                       = 0xffe1;
	public final static int XK_Shift_R                       = 0xffe2;
	public final static int XK_Control_L                     = 0xffe3;
	public final static int XK_Control_R                     = 0xffe4;
	public final static int XK_Caps_Lock                     = 0xffe5;
	public final static int XK_Shift_Lock                    = 0xffe6;

	public final static int XK_Meta_L                        = 0xffe7;
	public final static int XK_Meta_R                        = 0xffe8;
	public final static int XK_Alt_L                         = 0xffe9;
	public final static int XK_Alt_R                         = 0xffea;
	public final static int XK_Super_L                       = 0xffeb;
	public final static int XK_Super_R                       = 0xffec;
	public final static int XK_Hyper_L                       = 0xffed;
	public final static int XK_Hyper_R                       = 0xffee;
	public final static int XK_space                         = 0x0020;
	public final static int XK_exclam                        = 0x0021;
	public final static int XK_quotedbl                      = 0x0022;
	public final static int XK_numbersign                    = 0x0023;
	public final static int XK_dollar                        = 0x0024;
	public final static int XK_percent                       = 0x0025;
	public final static int XK_ampersand                     = 0x0026;
	public final static int XK_apostrophe                    = 0x0027;
	public final static int XK_quoteright                    = 0x0027;
	public final static int XK_parenleft                     = 0x0028;
	public final static int XK_parenright                    = 0x0029;
	public final static int XK_asterisk                      = 0x002a;
	public final static int XK_plus                          = 0x002b;
	public final static int XK_comma                         = 0x002c;
	public final static int XK_minus                         = 0x002d;
	public final static int XK_period                        = 0x002e;
	public final static int XK_slash                         = 0x002f;

	public final static int XK_0                             = 0x0030;
	public final static int XK_1                             = 0x0031;
	public final static int XK_2                             = 0x0032;
	public final static int XK_3                             = 0x0033;
	public final static int XK_4                             = 0x0034;
	public final static int XK_5                             = 0x0035;
	public final static int XK_6                             = 0x0036;
	public final static int XK_7                             = 0x0037;
	public final static int XK_8                             = 0x0038;
	public final static int XK_9                             = 0x0039;
	public final static int XK_colon                         = 0x003a;
	public final static int XK_semicolon                     = 0x003b;
	public final static int XK_less                          = 0x003c;
	public final static int XK_equal                         = 0x003d;
	public final static int XK_greater                       = 0x003e;
	public final static int XK_question                      = 0x003f;
	public final static int XK_at                            = 0x0040;
	public final static int XK_A                             = 0x0041;
	public final static int XK_B                             = 0x0042;
	public final static int XK_C                             = 0x0043;
	public final static int XK_D                             = 0x0044;
	public final static int XK_E                             = 0x0045;
	public final static int XK_F                             = 0x0046;
	public final static int XK_G                             = 0x0047;
	public final static int XK_H                             = 0x0048;
	public final static int XK_I                             = 0x0049;
	public final static int XK_J                             = 0x004a;
	public final static int XK_K                             = 0x004b;
	public final static int XK_L                             = 0x004c;
	public final static int XK_M                             = 0x004d;
	public final static int XK_N                             = 0x004e;
	public final static int XK_O                             = 0x004f;
	public final static int XK_P                             = 0x0050;
	public final static int XK_Q                             = 0x0051;
	public final static int XK_R                             = 0x0052;
	public final static int XK_S                             = 0x0053;
	public final static int XK_T                             = 0x0054;
	public final static int XK_U                             = 0x0055;
	public final static int XK_V                             = 0x0056;
	public final static int XK_W                             = 0x0057;
	public final static int XK_X                             = 0x0058;
	public final static int XK_Y                             = 0x0059;
	public final static int XK_Z                             = 0x005a;
	public final static int XK_bracketleft                   = 0x005b;
	public final static int XK_backslash                     = 0x005c;
	public final static int XK_bracketright                  = 0x005d;
	public final static int XK_asciicircum                   = 0x005e;
	public final static int XK_underscore                    = 0x005f;
	public final static int XK_grave                         = 0x0060;
	public final static int XK_quoteleft                     = 0x0060;
	public final static int XK_a                             = 0x0061;
	public final static int XK_b                             = 0x0062;
	public final static int XK_c                             = 0x0063;
	public final static int XK_d                             = 0x0064;
	public final static int XK_e                             = 0x0065;
	public final static int XK_f                             = 0x0066;
	public final static int XK_g                             = 0x0067;
	public final static int XK_h                             = 0x0068;
	public final static int XK_i                             = 0x0069;
	public final static int XK_j                             = 0x006a;
	public final static int XK_k                             = 0x006b;
	public final static int XK_l                             = 0x006c;
	public final static int XK_m                             = 0x006d;
	public final static int XK_n                             = 0x006e;
	public final static int XK_o                             = 0x006f;
	public final static int XK_p                             = 0x0070;
	public final static int XK_q                             = 0x0071;
	public final static int XK_r                             = 0x0072;
	public final static int XK_s                             = 0x0073;
	public final static int XK_t                             = 0x0074;
	public final static int XK_u                             = 0x0075;
	public final static int XK_v                             = 0x0076;
	public final static int XK_w                             = 0x0077;
	public final static int XK_x                             = 0x0078;
	public final static int XK_y                             = 0x0079;
	public final static int XK_z                             = 0x007a;
	public final static int XK_braceleft                     = 0x007b;
	public final static int XK_bar                           = 0x007c;
	public final static int XK_braceright                    = 0x007d;
	public final static int XK_asciitilde                    = 0x007e;

	public final static int XK_nobreakspace                  = 0x00a0;
	public final static int XK_exclamdown                    = 0x00a1;
	public final static int XK_cent                          = 0x00a2;
	public final static int XK_sterling                      = 0x00a3;
	public final static int XK_currency                      = 0x00a4;
	public final static int XK_yen                           = 0x00a5;
	public final static int XK_brokenbar                     = 0x00a6;
	public final static int XK_section                       = 0x00a7;
	public final static int XK_diaeresis                     = 0x00a8;
	public final static int XK_copyright                     = 0x00a9;
	public final static int XK_ordfeminine                   = 0x00aa;
	public final static int XK_guillemotleft                 = 0x00ab;
	public final static int XK_notsign                       = 0x00ac;
	public final static int XK_hyphen                        = 0x00ad;
	public final static int XK_registered                    = 0x00ae;
	public final static int XK_macron                        = 0x00af;
	public final static int XK_degree                        = 0x00b0;
	public final static int XK_plusminus                     = 0x00b1;
	public final static int XK_twosuperior                   = 0x00b2;
	public final static int XK_threesuperior                 = 0x00b3;
	public final static int XK_acute                         = 0x00b4;
	public final static int XK_mu                            = 0x00b5;
	public final static int XK_paragraph                     = 0x00b6;
	public final static int XK_periodcentered                = 0x00b7;
	public final static int XK_cedilla                       = 0x00b8;
	public final static int XK_onesuperior                   = 0x00b9;
	public final static int XK_masculine                     = 0x00ba;
	public final static int XK_guillemotright                = 0x00bb;
	public final static int XK_onequarter                    = 0x00bc;
	public final static int XK_onehalf                       = 0x00bd;
	public final static int XK_threequarters                 = 0x00be;
	public final static int XK_questiondown                  = 0x00bf;
	public final static int XK_Agrave                        = 0x00c0;
	public final static int XK_Aacute                        = 0x00c1;
	public final static int XK_Acircumflex                   = 0x00c2;
	public final static int XK_Atilde                        = 0x00c3;
	public final static int XK_Adiaeresis                    = 0x00c4;
	public final static int XK_Aring                         = 0x00c5;
	public final static int XK_AE                            = 0x00c6;
	public final static int XK_Ccedilla                      = 0x00c7;
	public final static int XK_Egrave                        = 0x00c8;
	public final static int XK_Eacute                        = 0x00c9;
	public final static int XK_Ecircumflex                   = 0x00ca;
	public final static int XK_Ediaeresis                    = 0x00cb;
	public final static int XK_Igrave                        = 0x00cc;
	public final static int XK_Iacute                        = 0x00cd;
	public final static int XK_Icircumflex                   = 0x00ce;
	public final static int XK_Idiaeresis                    = 0x00cf;
	public final static int XK_ETH                           = 0x00d0;
	public final static int XK_Eth                           = 0x00d0;
	public final static int XK_Ntilde                        = 0x00d1;
	public final static int XK_Ograve                        = 0x00d2;
	public final static int XK_Oacute                        = 0x00d3;
	public final static int XK_Ocircumflex                   = 0x00d4;
	public final static int XK_Otilde                        = 0x00d5;
	public final static int XK_Odiaeresis                    = 0x00d6;
	public final static int XK_multiply                      = 0x00d7;
	public final static int XK_Oslash                        = 0x00d8;
	public final static int XK_Ooblique                      = 0x00d8;
	public final static int XK_Ugrave                        = 0x00d9;
	public final static int XK_Uacute                        = 0x00da;
	public final static int XK_Ucircumflex                   = 0x00db;
	public final static int XK_Udiaeresis                    = 0x00dc;
	public final static int XK_Yacute                        = 0x00dd;
	public final static int XK_THORN                         = 0x00de;
	public final static int XK_Thorn                         = 0x00de;
	public final static int XK_ssharp                        = 0x00df;
	public final static int XK_agrave                        = 0x00e0;
	public final static int XK_aacute                        = 0x00e1;
	public final static int XK_acircumflex                   = 0x00e2;
	public final static int XK_atilde                        = 0x00e3;
	public final static int XK_adiaeresis                    = 0x00e4;
	public final static int XK_aring                         = 0x00e5;
	public final static int XK_ae                            = 0x00e6;
	public final static int XK_ccedilla                      = 0x00e7;
	public final static int XK_egrave                        = 0x00e8;
	public final static int XK_eacute                        = 0x00e9;
	public final static int XK_ecircumflex                   = 0x00ea;
	public final static int XK_ediaeresis                    = 0x00eb;
	public final static int XK_igrave                        = 0x00ec;
	public final static int XK_iacute                        = 0x00ed;
	public final static int XK_icircumflex                   = 0x00ee;
	public final static int XK_idiaeresis                    = 0x00ef;
	public final static int XK_eth                           = 0x00f0;
	public final static int XK_ntilde                        = 0x00f1;
	public final static int XK_ograve                        = 0x00f2;
	public final static int XK_oacute                        = 0x00f3;
	public final static int XK_ocircumflex                   = 0x00f4;
	public final static int XK_otilde                        = 0x00f5;
	public final static int XK_odiaeresis                    = 0x00f6;
	public final static int XK_division                      = 0x00f7;
	public final static int XK_oslash                        = 0x00f8;
	public final static int XK_ooblique                      = 0x00f8;
	public final static int XK_ugrave                        = 0x00f9;
	public final static int XK_uacute                        = 0x00fa;
	public final static int XK_ucircumflex                   = 0x00fb;
	public final static int XK_udiaeresis                    = 0x00fc;
	public final static int XK_yacute                        = 0x00fd;
	public final static int XK_thorn                         = 0x00fe;
	public final static int XK_ydiaeresis                    = 0x00ff;

	public final static int XK_ISO_Level3_Shift              = 0xfe03;

	public static int mapKeySymToLWJGLKeyCode(long keysym) {
		switch ((int)keysym) {
			case XK_BackSpace:
				return Keyboard.KEY_BACK;
			case XK_ISO_Left_Tab:
			case XK_Tab:
				return Keyboard.KEY_TAB;
			case XK_Return:
				return Keyboard.KEY_RETURN;
			case XK_Pause:
				return Keyboard.KEY_PAUSE;
			case XK_Scroll_Lock:
				return Keyboard.KEY_SCROLL;
			case XK_Sys_Req:
				return Keyboard.KEY_SYSRQ;
			case XK_Escape:
				return Keyboard.KEY_ESCAPE;
			case XK_Delete:
				return Keyboard.KEY_DELETE;

				/* Japanese keyboard support */

			case XK_Kanji:
				return Keyboard.KEY_KANJI;

				/* Cursor control & motion */

			case XK_Home:
				return Keyboard.KEY_HOME;
			case XK_Left:
				return Keyboard.KEY_LEFT;
			case XK_Up:
				return Keyboard.KEY_UP;
			case XK_Right:
				return Keyboard.KEY_RIGHT;
			case XK_Down:
				return Keyboard.KEY_DOWN;
			case XK_Page_Up:
				return Keyboard.KEY_PRIOR;
			case XK_Page_Down:
				return Keyboard.KEY_NEXT;
			case XK_End:
				return Keyboard.KEY_END;


				/* Misc Functions */

			case XK_Break:
				return Keyboard.KEY_PAUSE;
			case XK_Insert:
				return Keyboard.KEY_INSERT;
			case XK_Num_Lock:
				return Keyboard.KEY_NUMLOCK;

				/* Keypad Functions, keypad numbers cleverly chosen to map to ascii */

			case XK_KP_Space:
				return Keyboard.KEY_SPACE;
			case XK_KP_Tab:
				return Keyboard.KEY_TAB;
			case XK_KP_Enter:
				return Keyboard.KEY_NUMPADENTER;
			case XK_KP_F1:
				return Keyboard.KEY_F1;
			case XK_KP_F2:
				return Keyboard.KEY_F2;
			case XK_KP_F3:
				return Keyboard.KEY_F3;
			case XK_KP_F4:
				return Keyboard.KEY_F4;
			case XK_KP_Home:
				return Keyboard.KEY_HOME;
			case XK_KP_Left:
				return Keyboard.KEY_LEFT;
			case XK_KP_Up:
				return Keyboard.KEY_UP;
			case XK_KP_Right:
				return Keyboard.KEY_RIGHT;
			case XK_KP_Down:
				return Keyboard.KEY_DOWN;
			case XK_KP_Page_Up:
				return Keyboard.KEY_PRIOR;
			case XK_KP_Page_Down:
				return Keyboard.KEY_NEXT;
			case XK_KP_End:
				return Keyboard.KEY_END;
			case XK_KP_Insert:
				return Keyboard.KEY_INSERT;
			case XK_KP_Delete:
				return Keyboard.KEY_DELETE;
			case XK_KP_Equal:
				return Keyboard.KEY_NUMPADEQUALS;
			case XK_KP_Multiply:
				return Keyboard.KEY_MULTIPLY;
			case XK_KP_Add:
				return Keyboard.KEY_ADD;
			case XK_KP_Subtract:
				return Keyboard.KEY_SUBTRACT;
			case XK_KP_Decimal:
				return Keyboard.KEY_DECIMAL;
			case XK_KP_Divide:
				return Keyboard.KEY_DIVIDE;

			case XK_KP_0:
				return Keyboard.KEY_NUMPAD0;
			case XK_KP_1:
				return Keyboard.KEY_NUMPAD1;
			case XK_KP_2:
				return Keyboard.KEY_NUMPAD2;
			case XK_KP_3:
				return Keyboard.KEY_NUMPAD3;
			case XK_KP_4:
				return Keyboard.KEY_NUMPAD4;
			case XK_KP_5:
				return Keyboard.KEY_NUMPAD5;
			case XK_KP_6:
				return Keyboard.KEY_NUMPAD6;
			case XK_KP_7:
				return Keyboard.KEY_NUMPAD7;
			case XK_KP_8:
				return Keyboard.KEY_NUMPAD8;
			case XK_KP_9:
				return Keyboard.KEY_NUMPAD9;

				/*
				 * Auxilliary Functions; note the duplicate definitions for left and right
				 * function keys;  Sun keyboards and a few other manufactures have such
				 * function key groups on the left and/or right sides of the keyboard.
				 * We've not found a keyboard with more than 35 function keys total.
				 */

			case XK_F1:
				return Keyboard.KEY_F1;
			case XK_F2:
				return Keyboard.KEY_F2;
			case XK_F3:
				return Keyboard.KEY_F3;
			case XK_F4:
				return Keyboard.KEY_F4;
			case XK_F5:
				return Keyboard.KEY_F5;
			case XK_F6:
				return Keyboard.KEY_F6;
			case XK_F7:
				return Keyboard.KEY_F7;
			case XK_F8:
				return Keyboard.KEY_F8;
			case XK_F9:
				return Keyboard.KEY_F9;
			case XK_F10:
				return Keyboard.KEY_F10;
			case XK_F11:
				return Keyboard.KEY_F11;
			case XK_F12:
				return Keyboard.KEY_F12;
			case XK_F13:
				return Keyboard.KEY_F13;
			case XK_F14:
				return Keyboard.KEY_F14;
			case XK_F15:
				return Keyboard.KEY_F15;

				/* Modifiers */

			case XK_Shift_L:
				return Keyboard.KEY_LSHIFT;
			case XK_Shift_R:
				return Keyboard.KEY_RSHIFT;
			case XK_Control_L:
				return Keyboard.KEY_LCONTROL;
			case XK_Control_R:
				return Keyboard.KEY_RCONTROL;
			case XK_Caps_Lock:
				return Keyboard.KEY_CAPITAL;

			case XK_Meta_L:
				return Keyboard.KEY_LMENU;
			case XK_ISO_Level3_Shift:
			case XK_Meta_R:
				return Keyboard.KEY_RMENU;
			case XK_Alt_L:
				return Keyboard.KEY_LMENU;
			case XK_Alt_R:
				return Keyboard.KEY_RMENU;

			case XK_dead_grave:
				return Keyboard.KEY_GRAVE;
			case XK_dead_circumflex:
				return Keyboard.KEY_CIRCUMFLEX;

				/*
				 *  Latin 1
				 *  Byte 3 = 0
				 */
			case XK_space:
				return Keyboard.KEY_SPACE;
			case XK_apostrophe:
				return Keyboard.KEY_APOSTROPHE;
			case XK_comma:
				return Keyboard.KEY_COMMA;
			case XK_minus:
				return Keyboard.KEY_MINUS;
			case XK_period:
				return Keyboard.KEY_PERIOD;
			case XK_slash:
				return Keyboard.KEY_SLASH;
			case XK_0:
				return Keyboard.KEY_0;
			case XK_1:
				return Keyboard.KEY_1;
			case XK_2:
				return Keyboard.KEY_2;
			case XK_3:
				return Keyboard.KEY_3;
			case XK_4:
				return Keyboard.KEY_4;
			case XK_5:
				return Keyboard.KEY_5;
			case XK_6:
				return Keyboard.KEY_6;
			case XK_7:
				return Keyboard.KEY_7;
			case XK_8:
				return Keyboard.KEY_8;
			case XK_9:
				return Keyboard.KEY_9;
			case XK_colon:
				return Keyboard.KEY_COLON;
			case XK_semicolon:
				return Keyboard.KEY_SEMICOLON;
			case XK_equal:
				return Keyboard.KEY_EQUALS;
			case XK_at:
				return Keyboard.KEY_AT;
			case XK_bracketleft:
				return Keyboard.KEY_LBRACKET;
			case XK_bracketright:
				return Keyboard.KEY_RBRACKET;
			case XK_asciicircum:
				return Keyboard.KEY_CIRCUMFLEX;
			case XK_underscore:
				return Keyboard.KEY_UNDERLINE;
			case XK_grave:
				return Keyboard.KEY_GRAVE;
			case XK_a:
			case XK_A:
				return Keyboard.KEY_A;
			case XK_b:
			case XK_B:
				return Keyboard.KEY_B;
			case XK_c:
			case XK_C:
				return Keyboard.KEY_C;
			case XK_d:
			case XK_D:
				return Keyboard.KEY_D;
			case XK_e:
			case XK_E:
				return Keyboard.KEY_E;
			case XK_f:
			case XK_F:
				return Keyboard.KEY_F;
			case XK_g:
			case XK_G:
				return Keyboard.KEY_G;
			case XK_h:
			case XK_H:
				return Keyboard.KEY_H;
			case XK_i:
			case XK_I:
				return Keyboard.KEY_I;
			case XK_j:
			case XK_J:
				return Keyboard.KEY_J;
			case XK_k:
			case XK_K:
				return Keyboard.KEY_K;
			case XK_l:
			case XK_L:
				return Keyboard.KEY_L;
			case XK_m:
			case XK_M:
				return Keyboard.KEY_M;
			case XK_n:
			case XK_N:
				return Keyboard.KEY_N;
			case XK_o:
			case XK_O:
				return Keyboard.KEY_O;
			case XK_p:
			case XK_P:
				return Keyboard.KEY_P;
			case XK_q:
			case XK_Q:
				return Keyboard.KEY_Q;
			case XK_r:
			case XK_R:
				return Keyboard.KEY_R;
			case XK_s:
			case XK_S:
				return Keyboard.KEY_S;
			case XK_t:
			case XK_T:
				return Keyboard.KEY_T;
			case XK_u:
			case XK_U:
				return Keyboard.KEY_U;
			case XK_v:
			case XK_V:
				return Keyboard.KEY_V;
			case XK_w:
			case XK_W:
				return Keyboard.KEY_W;
			case XK_x:
			case XK_X:
				return Keyboard.KEY_X;
			case XK_y:
			case XK_Y:
				return Keyboard.KEY_Y;
			case XK_z:
			case XK_Z:
				return Keyboard.KEY_Z;
			default:
				return Keyboard.KEY_NONE;
		}
	}

}
