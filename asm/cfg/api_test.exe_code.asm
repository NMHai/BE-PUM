0x00401000:	pushl %eax
0x00401001:	pushl %ebx
0x00401002:	leal %eax, 0x403098
0x00401008:	pushl %eax
0x00401009:	call 0x004011f2
0x004011f2:	jmp SetCurrentDirectoryA@kernel32.dll
SetCurrentDirectoryA@kernel32.dll: API Node	
0x0040100e:	leal %eax, 0x40306d
0x00401014:	pushl $0x0<UINT8>
0x00401016:	pushl $0x80<UINT32>
0x0040101b:	pushl $0x2<UINT8>
0x0040101d:	pushl $0x0<UINT8>
0x0040101f:	pushl $0x1<UINT8>
0x00401021:	pushl $0x40000000<UINT32>
0x00401026:	pushl %eax
0x00401027:	call 0x00401180
0x00401180:	jmp CreateFileA@kernel32.dll
CreateFileA@kernel32.dll: API Node	
0x0040102c:	movl %ebx, %eax
0x0040102e:	pushl $0x0<UINT8>
0x00401030:	pushl $0x0<UINT8>
0x00401032:	pushl $0xa<UINT8>
0x00401034:	leal %eax, 0x403008
0x0040103a:	pushl %eax
0x0040103b:	pushl %ebx
0x0040103c:	call 0x004011fe
0x004011fe:	jmp WriteFile@kernel32.dll
WriteFile@kernel32.dll: API Node	
0x00401041:	pushl %ebx
0x00401042:	call 0x00401174
0x00401174:	jmp CloseHandle@kernel32.dll
CloseHandle@kernel32.dll: API Node	
0x00401047:	pushl $0x0<UINT8>
0x00401049:	leal %eax, 0x403080
0x0040104f:	pushl %eax
0x00401050:	leal %eax, 0x403076
0x00401056:	pushl %eax
0x00401057:	call 0x0040117a
0x0040117a:	jmp CopyFileA@kernel32.dll
CopyFileA@kernel32.dll: API Node	
0x0040105c:	leal %eax, 0x403090
0x00401062:	pushl %eax
0x00401063:	leal %eax, 0x40308a
0x00401069:	pushl %eax
0x0040106a:	call 0x00401198
0x00401198:	jmp FindFirstFileA@kernel32.dll
FindFirstFileA@kernel32.dll: API Node	
0x0040106f:	leal %ebx, 0x403090
0x00401075:	pushl %ebx
0x00401076:	pushl %eax
0x00401077:	call 0x0040119e
0x0040119e:	jmp FindNextFileA@kernel32.dll
FindNextFileA@kernel32.dll: API Node	
0x0040107c:	pushl %eax
0x0040107d:	call 0x00401192
0x00401192:	jmp FindClose@kernel32.dll
FindClose@kernel32.dll: API Node	
0x00401082:	leal %eax, 0x40306d
0x00401088:	pushl %eax
0x00401089:	call 0x004011b0
0x004011b0:	jmp GetFileAttributesA@kernel32.dll
GetFileAttributesA@kernel32.dll: API Node	
0x0040108e:	pushl $0x80<UINT32>
0x00401093:	leal %eax, 0x40306d
0x00401099:	pushl %eax
0x0040109a:	call 0x004011f8
0x004011f8:	jmp SetFileAttributesA@kernel32.dll
SetFileAttributesA@kernel32.dll: API Node	
0x0040109f:	leal %eax, 0x40306d
0x004010a5:	pushl %eax
0x004010a6:	call 0x004011b0
0x004010ab:	leal %eax, 0x40306d
0x004010b1:	pushl %eax
0x004010b2:	call 0x00401186
0x00401186:	jmp DeleteFileA@kernel32.dll
DeleteFileA@kernel32.dll: API Node	
0x004010b7:	leal %eax, 0x403060
0x004010bd:	pushl %eax
0x004010be:	call 0x004011ce
0x004011ce:	jmp GetStartupInfoA@kernel32.dll
GetStartupInfoA@kernel32.dll: API Node	
0x004010c3:	call 0x004011b6
0x004011b6:	jmp GetLastError@kernel32.dll
GetLastError@kernel32.dll: API Node	
0x004010c8:	call 0x004011e0
0x004011e0:	jmp GetVersion@kernel32.dll
GetVersion@kernel32.dll: API Node	
0x004010cd:	leal %ebx, 0x403042
0x004010d3:	pushl $0x64<UINT8>
0x004010d5:	pushl %ebx
0x004010d6:	call 0x004011d4
0x004011d4:	jmp GetSystemDirectoryA@kernel32.dll
GetSystemDirectoryA@kernel32.dll: API Node	
0x004010db:	leal %ebx, 0x403042
0x004010e1:	pushl %ebx
0x004010e2:	pushl $0x64<UINT8>
0x004010e4:	call 0x004011aa
0x004011aa:	jmp GetCurrentDirectoryA@kernel32.dll
GetCurrentDirectoryA@kernel32.dll: API Node	
0x004010e9:	leal %ebx, 0x403042
0x004010ef:	pushl $0x64<UINT8>
0x004010f1:	pushl %ebx
0x004010f2:	call 0x004011e6
0x004011e6:	jmp GetWindowsDirectoryA@kernel32.dll
GetWindowsDirectoryA@kernel32.dll: API Node	
0x004010f7:	call 0x004011a4
0x004011a4:	jmp GetCommandLineA@kernel32.dll
GetCommandLineA@kernel32.dll: API Node	
0x004010fc:	leal %eax, 0x403054
0x00401102:	pushl %eax
0x00401103:	call 0x004011da
0x004011da:	jmp GetSystemTime@kernel32.dll
GetSystemTime@kernel32.dll: API Node	
0x00401108:	leal %eax, 0x403022
0x0040110e:	pushl %eax
0x0040110f:	call 0x004011ec
0x004011ec:	jmp LoadLibraryA@kernel32.dll
LoadLibraryA@kernel32.dll: API Node	
0x00401114:	leal %eax, 0x403015
0x0040111a:	pushl %eax
0x0040111b:	call 0x004011ec
0x00401120:	leal %ebx, 0x40302d
0x00401126:	pushl %ebx
0x00401127:	pushl %eax
0x00401128:	call 0x004011c8
0x004011c8:	jmp GetProcAddress@kernel32.dll
GetProcAddress@kernel32.dll: API Node	
0x0040112d:	leal %ebx, 0x403042
0x00401133:	pushl %ebx
0x00401134:	pushl $0x64<UINT8>
0x00401136:	call 0x004011aa
0x0040113b:	pushl $0x64<UINT8>
0x0040113d:	pushl $0x403000<UINT32>
0x00401142:	pushl $0x0<UINT8>
0x00401144:	call 0x004011bc
0x004011bc:	jmp GetModuleFileNameA@kernel32.dll
GetModuleFileNameA@kernel32.dll: API Node	
0x00401149:	leal %eax, 0x403008
0x0040114f:	movl %ebx, $0x0<UINT32>
0x00401154:	pushl %ebx
0x00401155:	pushl %eax
0x00401156:	pushl %eax
0x00401157:	pushl %ebx
0x00401158:	call 0x00401204
0x00401204:	jmp MessageBoxA@user32.dll
MessageBoxA@user32.dll: API Node	
0x0040115d:	pushl $0x0<UINT8>
0x0040115f:	call 0x004011c2
0x004011c2:	jmp GetModuleHandleA@kernel32.dll
GetModuleHandleA@kernel32.dll: API Node	
0x00401164:	addl %eax, $0x116b<UINT32>
0x00401169:	pushl %eax
0x0040116a:	ret

0x0040116b:	addl %eax, %ebx
0x0040116d:	popl %eax
0x0040116e:	call 0x0040118c
0x0040118c:	jmp ExitProcess@kernel32.dll
ExitProcess@kernel32.dll: Exit Node	
