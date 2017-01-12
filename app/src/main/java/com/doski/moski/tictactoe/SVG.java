package com.doski.moski.tictactoe;

import android.graphics.Color;

/**
 * Created by moski on 1/11/17.
 */

public enum SVG {
	X(
			new String[]{
					"M16,16L112,112",
					"M112,16L16,112"
			},
			new int[]{
					Color.parseColor("#545454"),
					Color.parseColor("#545454")
			},

			120, 120
	),


	O(
			new String[]{
					"M64,16A48,48 0 1,0 64,112A48,48 0 1,0 64,16",
			},
			new int[]{
					Color.parseColor("#f2ebd4"),
			},

			120, 120

	),

	Empty(
			new String []{},
			new int []{},
			120,120
	);


	public final String[] glyphs;
	public final int[] colors;
	public final float width;
	public final float height;

	SVG(String[] glyphs, int[] colors, float width, float height) {
		this.glyphs = glyphs;
		this.colors = colors;
		this.width = width;
		this.height = height;
	}
}
