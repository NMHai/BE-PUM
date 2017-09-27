0x004052e0:	pusha
0x004052e1:	movl %esi, $0x405000<UINT32>
0x004052e6:	leal %edi, -16384(%esi)
0x004052ec:	pushl %edi
0x004052ed:	jmp 0x004052fa
0x004052fa:	movl %ebx, (%esi)
0x004052fc:	subl %esi, $0xfffffffc<UINT8>
0x004052ff:	adcl %ebx, %ebx
0x00405301:	jb 0x004052f0
0x004052f0:	movb %al, (%esi)
0x004052f2:	incl %esi
0x004052f3:	movb (%edi), %al
0x004052f5:	incl %edi
0x004052f6:	addl %ebx, %ebx
0x004052f8:	jne 0x00405301
0x00405303:	movl %eax, $0x1<UINT32>
0x00405308:	addl %ebx, %ebx
0x0040530a:	jne 0x00405313
0x00405313:	adcl %eax, %eax
0x00405315:	addl %ebx, %ebx
0x00405317:	jae 0x00405308
0x00405319:	jne 0x00405324
0x00405324:	xorl %ecx, %ecx
0x00405326:	subl %eax, $0x3<UINT8>
0x00405329:	jb 0x00405338
0x0040532b:	shll %eax, $0x8<UINT8>
0x0040532e:	movb %al, (%esi)
0x00405330:	incl %esi
0x00405331:	xorl %eax, $0xffffffff<UINT8>
0x00405334:	je 0x004053aa
0x00405336:	movl %ebp, %eax
0x00405338:	addl %ebx, %ebx
0x0040533a:	jne 0x00405343
0x00405343:	adcl %ecx, %ecx
0x00405345:	addl %ebx, %ebx
0x00405347:	jne 0x00405350
0x00405350:	adcl %ecx, %ecx
0x00405352:	jne 0x00405374
0x00405374:	cmpl %ebp, $0xfffff300<UINT32>
0x0040537a:	adcl %ecx, $0x1<UINT8>
0x0040537d:	leal %edx, (%edi,%ebp)
0x00405380:	cmpl %ebp, $0xfffffffc<UINT8>
0x00405383:	jbe 0x00405394
0x00405394:	movl %eax, (%edx)
0x00405396:	addl %edx, $0x4<UINT8>
0x00405399:	movl (%edi), %eax
0x0040539b:	addl %edi, $0x4<UINT8>
0x0040539e:	subl %ecx, $0x4<UINT8>
0x004053a1:	ja 0x00405394
0x004053a3:	addl %edi, %ecx
0x004053a5:	jmp 0x004052f6
0x0040533c:	movl %ebx, (%esi)
0x0040533e:	subl %esi, $0xfffffffc<UINT8>
0x00405341:	adcl %ebx, %ebx
0x00405354:	incl %ecx
0x00405355:	addl %ebx, %ebx
0x00405357:	jne 0x00405360
0x00405360:	adcl %ecx, %ecx
0x00405362:	addl %ebx, %ebx
0x00405364:	jae 0x00405355
0x00405366:	jne 0x00405371
0x00405371:	addl %ecx, $0x2<UINT8>
0x00405385:	movb %al, (%edx)
0x00405387:	incl %edx
0x00405388:	movb (%edi), %al
0x0040538a:	incl %edi
0x0040538b:	decl %ecx
0x0040538c:	jne 0x00405385
0x0040538e:	jmp 0x004052f6
0x00405359:	movl %ebx, (%esi)
0x0040535b:	subl %esi, $0xfffffffc<UINT8>
0x0040535e:	adcl %ebx, %ebx
0x0040530c:	movl %ebx, (%esi)
0x0040530e:	subl %esi, $0xfffffffc<UINT8>
0x00405311:	adcl %ebx, %ebx
0x00405349:	movl %ebx, (%esi)
0x0040534b:	subl %esi, $0xfffffffc<UINT8>
0x0040534e:	adcl %ebx, %ebx
0x0040531b:	movl %ebx, (%esi)
0x0040531d:	subl %esi, $0xfffffffc<UINT8>
0x00405320:	adcl %ebx, %ebx
0x00405322:	jae 0x00405308
0x00405368:	movl %ebx, (%esi)
0x0040536a:	subl %esi, $0xfffffffc<UINT8>
0x0040536d:	adcl %ebx, %ebx
0x0040536f:	jae 0x00405355
0x004053aa:	popl %esi
0x004053ab:	movl %edi, %esi
0x004053ad:	movl %ecx, $0x1c<UINT32>
0x004053b2:	movb %al, (%edi)
0x004053b4:	incl %edi
0x004053b5:	subb %al, $0xffffffe8<UINT8>
0x004053b7:	cmpb %al, $0x1<UINT8>
0x004053b9:	ja 0x004053b2
0x004053bb:	cmpb (%edi), $0x0<UINT8>
0x004053be:	jne -14
0x004053c0:	movl %eax, (%edi)
0x004053c2:	movb %bl, 0x4(%edi)
0x004053c5:	shrw %ax, $0x8<UINT8>
0x004053c9:	roll %eax, $0x10<UINT8>
0x004053cc:	xchgb %ah, %al
0x004053ce:	subl %eax, %edi
0x004053d0:	subb %bl, $0xffffffe8<UINT8>
0x004053d3:	addl %eax, %esi
0x004053d5:	movl (%edi), %eax
0x004053d7:	addl %edi, $0x5<UINT8>
0x004053da:	movb %al, %bl
0x004053dc:	loop 0x004053b7
0x004053de:	leal %edi, 0x3000(%esi)
0x004053e4:	movl %eax, (%edi)
0x004053e6:	orl %eax, %eax
0x004053e8:	je 0x00405426
0x004053ea:	movl %ebx, 0x4(%edi)
0x004053ed:	leal %eax, 0x5000(%eax,%esi)
0x004053f4:	addl %ebx, %esi
0x004053f6:	pushl %eax
0x004053f7:	addl %edi, $0x8<UINT8>
0x004053fa:	call LoadLibraryA@KERNEL32.DLL
LoadLibraryA@kernel32.dll: API Node	
0x00405400:	xchgl %ebp, %eax
0x00405401:	movb %al, (%edi)
0x00405403:	incl %edi
0x00405404:	orb %al, %al
0x00405406:	je 0x004053e4
0x00405408:	movl %ecx, %edi
0x0040540a:	pushl %edi
0x0040540b:	decl %eax
0x0040540c:	repn scasb %al, %es:(%edi)
0x0040540e:	pushl %ebp
0x0040540f:	call GetProcAddress@KERNEL32.DLL
GetProcAddress@kernel32.dll: API Node	
0x00405415:	orl %eax, %eax
0x00405417:	je 7
0x00405419:	movl (%ebx), %eax
0x0040541b:	addl %ebx, $0x4<UINT8>
0x0040541e:	jmp 0x00405401
GetProcAddress@KERNEL32.DLL: API Node	
LoadLibraryA@KERNEL32.DLL: API Node	
0x00405426:	movl %ebp, 0x5044(%esi)
0x0040542c:	leal %edi, -4096(%esi)
0x00405432:	movl %ebx, $0x1000<UINT32>
0x00405437:	pushl %eax
0x00405438:	pushl %esp
0x00405439:	pushl $0x4<UINT8>
0x0040543b:	pushl %ebx
0x0040543c:	pushl %edi
0x0040543d:	call VirtualProtect@kernel32.dll
VirtualProtect@kernel32.dll: API Node	
0x0040543f:	leal %eax, 0x1d7(%edi)
0x00405445:	andb (%eax), $0x7f<UINT8>
0x00405448:	andb 0x28(%eax), $0x7f<UINT8>
0x0040544c:	popl %eax
0x0040544d:	pushl %eax
0x0040544e:	pushl %esp
0x0040544f:	pushl %eax
0x00405450:	pushl %ebx
0x00405451:	pushl %edi
0x00405452:	call VirtualProtect@kernel32.dll
0x00405454:	popl %eax
0x00405455:	popa
0x00405456:	leal %eax, -128(%esp)
0x0040545a:	pushl $0x0<UINT8>
0x0040545c:	cmpl %esp, %eax
0x0040545e:	jne 0x0040545a
0x00405460:	subl %esp, $0xffffff80<UINT8>
0x00405463:	jmp 0x00401000
0x00401000:	pushl %eax
0x00401001:	pushl %ebx
0x00401002:	leal %eax, 0x403098
0x00401008:	pushl %eax
0x00401009:	call 0x004011f2
0x004011f2:	jmp SetCurrentDirectoryA@KERNEL32.DLL
SetCurrentDirectoryA@KERNEL32.DLL: API Node	
0x0040100e:	leal %eax, 0x40306d
0x00401014:	pushl $0x0<UINT8>
0x00401016:	pushl $0x80<UINT32>
0x0040101b:	pushl $0x2<UINT8>
0x0040101d:	pushl $0x0<UINT8>
0x0040101f:	pushl $0x1<UINT8>
0x00401021:	pushl $0x40000000<UINT32>
0x00401026:	pushl %eax
0x00401027:	call 0x00401180
0x00401180:	jmp CreateFileA@KERNEL32.DLL
CreateFileA@KERNEL32.DLL: API Node	
0x0040102c:	movl %ebx, %eax
0x0040102e:	pushl $0x0<UINT8>
0x00401030:	pushl $0x0<UINT8>
0x00401032:	pushl $0xa<UINT8>
0x00401034:	leal %eax, 0x403008
0x0040103a:	pushl %eax
0x0040103b:	pushl %ebx
0x0040103c:	call 0x004011fe
0x004011fe:	jmp WriteFile@KERNEL32.DLL
WriteFile@KERNEL32.DLL: API Node	
0x00401041:	pushl %ebx
0x00401042:	call 0x00401174
0x00401174:	jmp CloseHandle@KERNEL32.DLL
CloseHandle@KERNEL32.DLL: API Node	
0x00401047:	pushl $0x0<UINT8>
0x00401049:	leal %eax, 0x403080
0x0040104f:	pushl %eax
0x00401050:	leal %eax, 0x403076
0x00401056:	pushl %eax
0x00401057:	call 0x0040117a
0x0040117a:	jmp CopyFileA@KERNEL32.DLL
CopyFileA@KERNEL32.DLL: API Node	
0x0040105c:	leal %eax, 0x403090
0x00401062:	pushl %eax
0x00401063:	leal %eax, 0x40308a
0x00401069:	pushl %eax
0x0040106a:	call 0x00401198
0x00401198:	jmp FindFirstFileA@KERNEL32.DLL
FindFirstFileA@KERNEL32.DLL: API Node	
0x0040106f:	leal %ebx, 0x403090
0x00401075:	pushl %ebx
0x00401076:	pushl %eax
0x00401077:	call 0x0040119e
0x0040119e:	jmp FindNextFileA@KERNEL32.DLL
FindNextFileA@KERNEL32.DLL: API Node	
0x0040107c:	pushl %eax
0x0040107d:	call 0x00401192
0x00401192:	jmp FindClose@KERNEL32.DLL
FindClose@KERNEL32.DLL: API Node	
0x00401082:	leal %eax, 0x40306d
0x00401088:	pushl %eax
0x00401089:	call 0x004011b0
0x004011b0:	jmp GetFileAttributesA@KERNEL32.DLL
GetFileAttributesA@KERNEL32.DLL: API Node	
0x0040108e:	pushl $0x80<UINT32>
0x00401093:	leal %eax, 0x40306d
0x00401099:	pushl %eax
0x0040109a:	call 0x004011f8
0x004011f8:	jmp SetFileAttributesA@KERNEL32.DLL
SetFileAttributesA@KERNEL32.DLL: API Node	
0x0040109f:	leal %eax, 0x40306d
0x004010a5:	pushl %eax
0x004010a6:	call 0x004011b0
0x004010ab:	leal %eax, 0x40306d
0x004010b1:	pushl %eax
0x004010b2:	call 0x00401186
0x00401186:	jmp DeleteFileA@KERNEL32.DLL
DeleteFileA@KERNEL32.DLL: API Node	
0x004010b7:	leal %eax, 0x403060
0x004010bd:	pushl %eax
0x004010be:	call 0x004011ce
0x004011ce:	jmp GetStartupInfoA@KERNEL32.DLL
GetStartupInfoA@KERNEL32.DLL: API Node	
0x004010c3:	call 0x004011b6
0x004011b6:	jmp GetLastError@KERNEL32.DLL
GetLastError@KERNEL32.DLL: API Node	
0x004010c8:	call 0x004011e0
0x004011e0:	jmp GetVersion@KERNEL32.DLL
GetVersion@KERNEL32.DLL: API Node	
0x004010cd:	leal %ebx, 0x403042
0x004010d3:	pushl $0x64<UINT8>
0x004010d5:	pushl %ebx
0x004010d6:	call 0x004011d4
0x004011d4:	jmp GetSystemDirectoryA@KERNEL32.DLL
GetSystemDirectoryA@KERNEL32.DLL: API Node	
0x004010db:	leal %ebx, 0x403042
0x004010e1:	pushl %ebx
0x004010e2:	pushl $0x64<UINT8>
0x004010e4:	call 0x004011aa
0x004011aa:	jmp GetCurrentDirectoryA@kernel32.dll
GetCurrentDirectoryA@KERNEL32.DLL: API Node	
0x004010e9:	leal %ebx, 0x403042
0x004010ef:	pushl $0x64<UINT8>
0x004010f1:	pushl %ebx
0x004010f2:	call 0x004011e6
0x004011e6:	jmp GetWindowsDirectoryA@KERNEL32.DLL
GetWindowsDirectoryA@KERNEL32.DLL: API Node	
0x004010f7:	call 0x004011a4
0x004011a4:	jmp GetCommandLineA@KERNEL32.DLL
GetCommandLineA@KERNEL32.DLL: API Node	
0x004010fc:	leal %eax, 0x403054
0x00401102:	pushl %eax
0x00401103:	call 0x004011da
0x004011da:	jmp GetSystemTime@KERNEL32.DLL
GetSystemTime@KERNEL32.DLL: API Node	
0x00401108:	leal %eax, 0x403022
0x0040110e:	pushl %eax
0x0040110f:	call 0x004011ec
0x004011ec:	jmp LoadLibraryA@KERNEL32.DLL
0x00401114:	leal %eax, 0x403015
0x0040111a:	pushl %eax
0x0040111b:	call 0x004011ec
0x00401120:	leal %ebx, 0x40302d
0x00401126:	pushl %ebx
0x00401127:	pushl %eax
0x00401128:	call 0x004011c8
0x004011c8:	jmp GetProcAddress@KERNEL32.DLL
0x0040112d:	leal %ebx, 0x403042
0x00401133:	pushl %ebx
0x00401134:	pushl $0x64<UINT8>
0x00401136:	call 0x004011aa
GetCurrentDirectoryA@kernel32.dll: API Node	
0x0040113b:	pushl $0x64<UINT8>
0x0040113d:	pushl $0x403000<UINT32>
0x00401142:	pushl $0x0<UINT8>
0x00401144:	call 0x004011bc
0x004011bc:	jmp GetModuleFileNameA@KERNEL32.DLL
GetModuleFileNameA@KERNEL32.DLL: API Node	
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
0x004011c2:	jmp GetModuleHandleA@KERNEL32.DLL
GetModuleHandleA@KERNEL32.DLL: API Node	
0x00401164:	addl %eax, $0x116b<UINT32>
0x00401169:	pushl %eax
0x0040116a:	ret

0x0040116b:	addl %eax, %ebx
0x0040116d:	popl %eax
0x0040116e:	call 0x0040118c
0x0040118c:	jmp ExitProcess@KERNEL32.DLL
ExitProcess@KERNEL32.DLL: Exit Node	
