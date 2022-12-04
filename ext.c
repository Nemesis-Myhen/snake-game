#include <windows.h>
#include "snake.h"

JNIEXPORT jbyte JNICALL Java_snake_getInput(JNIEnv *env, jclass class)
{
	jbyte ret = 0;

	if(GetAsyncKeyState(VK_LEFT))
	{
		ret += 8;
	}
	if(GetAsyncKeyState(VK_UP))
	{
		ret += 4;
	}
	if(GetAsyncKeyState(VK_RIGHT))
	{
		ret += 2;
	}
	if(GetAsyncKeyState(VK_DOWN))
	{
		ret += 1;
	}

	return ret;
}

JNIEXPORT void JNICALL Java_snake_clearScreen(JNIEnv *env, jclass class)
{
	DWORD cw = 0;
	HANDLE h = GetStdHandle(STD_OUTPUT_HANDLE);
	FillConsoleOutputCharacter(h, 0, 160*48, (COORD){0,0}, &cw);
	SetConsoleCursorPosition(h,(COORD){0,0});
}

JNIEXPORT jbyte JNICALL Java_snake_getInputUp(JNIEnv *env, jclass class)
{
	if(GetAsyncKeyState(VK_UP))
	{
		return 1;
	}
	else return 0;
}

JNIEXPORT jbyte JNICALL Java_snake_getInputDown(JNIEnv *env, jclass class)
{
	if(GetAsyncKeyState(VK_DOWN))
	{
		return 1;
	}
	else return 0;
}

JNIEXPORT jbyte JNICALL Java_snake_getInputLeft(JNIEnv *env, jclass class)
{
	if(GetAsyncKeyState(VK_LEFT))
	{
		return 1;
	}
	else return 0;
}

JNIEXPORT jbyte JNICALL Java_snake_getInputRight(JNIEnv *env, jclass class)
{
	if(GetAsyncKeyState(VK_RIGHT))
	{
		return 1;
	}
	else return 0;
}
