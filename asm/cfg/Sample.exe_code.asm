0x00401570:	subl %esp, $0x1c<UINT8>
0x00401573:	movl (%esp), $0x1<UINT32>
0x0040157a:	call __set_app_type@msvcrt.dll
__set_app_type@msvcrt.dll: API Node	
0x00401580:	call 0x00401180
0x00401180:	pushl %ebp
0x00401181:	movl %ebp, %esp
0x00401183:	pushl %edi
0x00401184:	pushl %esi
0x00401185:	pushl %ebx
0x00401186:	subl %esp, $0x5c<UINT8>
0x00401189:	movl %eax, 0x4687e0
0x0040118e:	testl %eax, %eax
0x00401190:	je 28
0x00401192:	movl 0x8(%esp), $0x0<UINT32>
0x0040119a:	movl 0x4(%esp), $0x2<UINT32>
0x004011a2:	movl (%esp), $0x0<UINT32>
0x004011a9:	call 0x00409190
0x00409190:	pushl %esi
0x00409191:	pushl %ebx
0x00409192:	subl %esp, $0x14<UINT8>
0x00409195:	cmpl 0x490044, $0x2<UINT8>
0x0040919c:	movl %eax, 0x24(%esp)
0x004091a0:	je 10
0x004091a2:	movl 0x490044, $0x2<UINT32>
0x004091ac:	cmpl %eax, $0x2<UINT8>
0x004091af:	je 0x004091c3
0x004091c3:	movl %esi, $0x492014<UINT32>
0x004091c8:	subl %esi, $0x492014<UINT32>
0x004091ce:	sarl %esi, $0x2<UINT8>
0x004091d1:	testl %esi, %esi
0x004091d3:	jle 0x004091b6
0x004091b6:	addl %esp, $0x14<UINT8>
0x004091b9:	movl %eax, $0x1<UINT32>
0x004091be:	popl %ebx
0x004091bf:	popl %esi
0x004091c0:	ret $0xc<UINT16>

0x004011ab:	subl %esp, $0xc<UINT8>
0x004011ae:	movl (%esp), $0x401000<UINT32>
0x004011b5:	call 0x004143b0
0x004143b0:	jmp SetUnhandledExceptionFilter@kernel32.dll
SetUnhandledExceptionFilter@kernel32.dll: API Node	
0x004011ba:	subl %esp, $0x4<UINT8>
0x004011bd:	call 0x00409240
0x00409240:	pushfl
0x00409241:	pushfl
0x00409242:	popl %eax
0x00409243:	movl %edx, %eax
0x00409245:	xorl %eax, $0x200000<UINT32>
0x0040924a:	pushl %eax
0x0040924b:	popfl
0x0040924c:	pushfl
0x0040924d:	popl %eax
0x0040924e:	popfl
0x0040924f:	xorl %eax, %edx
0x00409251:	testl %eax, $0x200000<UINT32>
0x00409256:	je 0x00409301
0x00409301:	rep ret

0x004011c2:	call 0x00409230
0x00409230:	fninit
0x00409232:	ret

0x004011c7:	movl %eax, 0x45f000
0x004011cc:	testb %al, $0x2<UINT8>
0x004011ce:	je 829
0x004011d4:	call 0x004143b8
0x004143b8:	jmp GetCommandLineA@kernel32.dll
GetCommandLineA@kernel32.dll: API Node	
0x004011d9:	orl %ecx, $0xffffffff<UINT8>
0x004011dc:	movl -76(%ebp), %esp
0x004011df:	movl %esi, %eax
0x004011e1:	xorl %eax, %eax
0x004011e3:	movl %edi, %esi
0x004011e5:	repn scasb %al, %es:(%edi)
0x004011e7:	notl %ecx
0x004011e9:	leal %eax, 0xf(%ecx,%ecx)
0x004011ed:	andl %eax, $0xfffffff0<UINT8>
0x004011f0:	call 0x00409920
0x00409920:	pushl %ecx
0x00409921:	pushl %eax
0x00409922:	cmpl %eax, $0x1000<UINT32>
0x00409927:	leal %ecx, 0xc(%esp)
0x0040992b:	jb 0x00409942
0x00409942:	subl %ecx, %eax
0x00409944:	orl (%ecx), $0x0<UINT8>
0x00409947:	popl %eax
0x00409948:	popl %ecx
0x00409949:	ret

0x004011f5:	subl %esp, %eax
0x004011f7:	leal %eax, 0x14(%esp)
0x004011fb:	movl %edx, %eax
0x004011fd:	movl -72(%ebp), %eax
0x00401200:	movl %eax, 0x45f000
0x00401205:	movl -28(%ebp), $0x0<UINT32>
0x0040120c:	movl -60(%ebp), $0x0<UINT32>
0x00401213:	movl -64(%ebp), $0x0<UINT32>
0x0040121a:	andl %eax, $0x40<UINT8>
0x0040121d:	cmpl %eax, $0x1<UINT8>
0x00401220:	sbbl %eax, %eax
0x00401222:	movl -68(%ebp), %eax
0x00401225:	xorl %eax, %eax
0x00401227:	andl -68(%ebp), $0xffffc000<UINT32>
0x0040122e:	addl -68(%ebp), $0x4010<UINT32>
0x00401235:	addl %esi, $0x1<UINT8>
0x00401238:	movzbl %ebx, -1(%esi)
0x0040123c:	movsbl %ecx, %bl
0x0040123f:	testl %ecx, %ecx
0x00401241:	je 0x00401336
0x00401247:	cmpb %bl, $0x3f<UINT8>
0x0040124a:	je 688
0x00401250:	jg 0x004012c0
0x00401252:	cmpb %bl, $0x27<UINT8>
0x00401255:	je 605
0x0040125b:	cmpb %bl, $0x2a<UINT8>
0x0040125e:	nop
0x00401260:	je 666
0x00401266:	cmpb %bl, $0x22<UINT8>
0x00401269:	jne 0x00401422
0x0040126f:	movl %ebx, %eax
0x00401271:	sarl %ebx
0x00401273:	je 0x00401556
0x00401556:	movl %ebx, %edx
0x00401558:	jmp 0x0040128b
0x0040128b:	testb %al, $0x1<UINT8>
0x0040128d:	jne 22
0x0040128f:	cmpl -60(%ebp), $0x27<UINT8>
0x00401293:	je 16
0x00401295:	xorl -60(%ebp), %ecx
0x00401298:	movl %edx, %ebx
0x0040129a:	xorl %eax, %eax
0x0040129c:	movl -64(%ebp), $0x1<UINT32>
0x004012a3:	jmp 0x00401235
0x004012c0:	cmpb %bl, $0x5c<UINT8>
0x004012c3:	je 0x004014b0
0x004012c9:	cmpb %bl, $0x7f<UINT8>
0x004012cc:	je 558
0x004012d2:	cmpb %bl, $0x5b<UINT8>
0x004012d5:	jne 0x00401422
0x00401422:	testl %eax, %eax
0x00401424:	leal %edi, (%edx,%eax)
0x00401427:	je 0x0040154f
0x0040154f:	movl %edi, %edx
0x00401551:	jmp 0x0040143b
0x0040143b:	movl %eax, -60(%ebp)
0x0040143e:	testl %eax, %eax
0x00401440:	jne 0x00401495
0x00401495:	leal %edx, 0x1(%edi)
0x00401498:	xorl %eax, %eax
0x0040149a:	movb (%edi), %bl
0x0040149c:	jmp 0x00401235
0x004014b0:	addl %eax, $0x1<UINT8>
0x004014b3:	jmp 0x00401235
0x0040142d:	leal %esi, (%esi)
0x00401430:	addl %edx, $0x1<UINT8>
0x00401433:	cmpl %edx, %edi
0x00401435:	movb -1(%edx), $0x5c<UINT8>
0x00401439:	jne -11
0x00401336:	testl %eax, %eax
0x00401338:	je 0x0040155d
0x0040155d:	movl %eax, %edx
0x0040155f:	nop
0x00401560:	jmp 0x0040134b
0x0040134b:	cmpl -64(%ebp), $0x0<UINT8>
0x0040134f:	jne 0x00401356
0x00401356:	movb (%eax), $0x0<UINT8>
0x00401359:	leal %eax, -40(%ebp)
0x0040135c:	movl 0xc(%esp), %eax
0x00401360:	movl 0x8(%esp), $0x0<UINT32>
0x00401368:	movl %eax, -68(%ebp)
0x0040136b:	movl 0x4(%esp), %eax
0x0040136f:	movl %eax, -72(%ebp)
0x00401372:	movl (%esp), %eax
0x00401375:	call 0x0040ece0
0x0040ece0:	pushl %ebp
0x0040ece1:	movl %ebp, %esp
0x0040ece3:	pushl %edi
0x0040ece4:	pushl %esi
0x0040ece5:	pushl %ebx
0x0040ece6:	subl %esp, $0x1c<UINT8>
0x0040ece9:	movl %esi, 0x14(%ebp)
0x0040ecec:	movl %ebx, 0x8(%ebp)
0x0040ecef:	cmpl (%esi), $0x468e7e<UINT32>
0x0040ecf5:	je 13
0x0040ecf7:	movl %eax, %esi
0x0040ecf9:	call 0x0040e340
0x0040e340:	pushl %ebp
0x0040e341:	pushl %edi
0x0040e342:	pushl %esi
0x0040e343:	movl %esi, %eax
0x0040e345:	pushl %ebx
0x0040e346:	subl %esp, $0x1c<UINT8>
0x0040e349:	testl %eax, %eax
0x0040e34b:	je 71
0x0040e34d:	movl %eax, 0xc(%eax)
0x0040e350:	leal %edi, 0x1(%eax)
0x0040e353:	leal %ebp, (,%edi,4)
0x0040e35a:	movl (%esp), %ebp
0x0040e35d:	call 0x00414280
0x00414280:	jmp malloc@msvcrt.dll
malloc@msvcrt.dll: API Node	
0x0040e362:	movl %ebx, %eax
0x0040e364:	testl %ebx, %ebx
0x0040e366:	movl 0x8(%esi), %eax
0x0040e369:	movl %eax, $0x3<UINT32>
0x0040e36e:	je 38
0x0040e370:	testl %edi, %edi
0x0040e372:	movl %edx, %edi
0x0040e374:	movl 0x4(%esi), $0x0<UINT32>
0x0040e37b:	jle 23
0x0040e37d:	leal %ecx, -4(%ebp)
0x0040e380:	jmp 0x0040e385
0x0040e385:	movl (%ebx,%ecx), $0x0<UINT32>
0x0040e38c:	subl %ecx, $0x4<UINT8>
0x0040e38f:	subl %edx, $0x1<UINT8>
0x0040e392:	jne -18
0x0040e394:	xorl %eax, %eax
0x0040e396:	addl %esp, $0x1c<UINT8>
0x0040e399:	popl %ebx
0x0040e39a:	popl %esi
0x0040e39b:	popl %edi
0x0040e39c:	popl %ebp
0x0040e39d:	ret

0x0040ecfe:	movl (%esi), $0x468e7e<UINT32>
0x0040ed04:	movl (%esp), %esi
0x0040ed07:	movl %ecx, 0x10(%ebp)
0x0040ed0a:	movl %eax, %ebx
0x0040ed0c:	movl %edx, 0xc(%ebp)
0x0040ed0f:	call 0x0040e6f0
0x0040e6f0:	pushl %ebp
0x0040e6f1:	movl %ebp, %esp
0x0040e6f3:	pushl %edi
0x0040e6f4:	pushl %esi
0x0040e6f5:	pushl %ebx
0x0040e6f6:	movl %ebx, %eax
0x0040e6f8:	subl %esp, $0x6c<UINT8>
0x0040e6fb:	movl -48(%ebp), %edx
0x0040e6fe:	movl -88(%ebp), %ecx
0x0040e701:	movl (%esp), %eax
0x0040e704:	call 0x004141e8
0x004141e8:	jmp strlen@msvcrt.dll
strlen@msvcrt.dll: API Node	
0x0040e709:	leal %edx, 0x1(%eax)
0x0040e70c:	addl %eax, $0x10<UINT8>
0x0040e70f:	andl %eax, $0xfffffff0<UINT8>
0x0040e712:	call 0x00409920
0x0040992d:	subl %ecx, $0x1000<UINT32>
0x0040e717:	subl %esp, %eax
0x0040e719:	leal %eax, 0xc(%esp)
0x0040e71d:	movl 0x8(%esp), %edx
0x0040e721:	movl 0x4(%esp), %ebx
0x0040e725:	movl (%esp), %eax
0x0040e728:	call 0x004141f8
0x004141f8:	jmp memcpy@msvcrt.dll
memcpy@msvcrt.dll: API Node	
0x004141fe:	nop
0x004141ff:	nop
0x00414200:	jmp fread@msvcrt.dll
fread@msvcrt.dll: API Node	
0x00414206:	nop
0x00414207:	nop
0x00414208:	jmp fwrite@msvcrt.dll
fwrite@msvcrt.dll: API Node	
0x0041420e:	nop
0x0041420f:	nop
0x00414210:	jmp getwc@msvcrt.dll
getwc@msvcrt.dll: API Node	
0x00414216:	nop
0x00414217:	nop
0x00414218:	jmp putwc@msvcrt.dll
putwc@msvcrt.dll: API Node	
0x0041421e:	nop
0x0041421f:	nop
0x00414220:	jmp ungetc@msvcrt.dll
ungetc@msvcrt.dll: API Node	
0x00414226:	nop
0x00414227:	nop
0x00414228:	jmp _filbuf@msvcrt.dll
_filbuf@msvcrt.dll: API Node	
0x0041422e:	nop
0x0041422f:	nop
0x00414230:	jmp fflush@msvcrt.dll
fflush@msvcrt.dll: API Node	
0x00414236:	nop
0x00414237:	nop
0x00414238:	jmp fseek@msvcrt.dll
fseek@msvcrt.dll: API Node	
0x0041423e:	nop
0x0041423f:	nop
0x00414240:	jmp ftell@msvcrt.dll
ftell@msvcrt.dll: API Node	
0x00414246:	nop
0x00414247:	nop
0x00414248:	jmp ungetwc@msvcrt.dll
ungetwc@msvcrt.dll: API Node	
0x0041424e:	nop
0x0041424f:	nop
0x00414250:	jmp _flsbuf@msvcrt.dll
_flsbuf@msvcrt.dll: API Node	
0x00414256:	nop
0x00414257:	nop
0x00414258:	jmp abort@msvcrt.dll
abort@msvcrt.dll: API Node	
0x0041425e:	nop
0x0041425f:	nop
0x00414260:	jmp setlocale@msvcrt.dll
setlocale@msvcrt.dll: API Node	
0x00414266:	nop
0x00414267:	nop
0x00414268:	jmp strcmp@msvcrt.dll
strcmp@msvcrt.dll: API Node	
0x0041426e:	nop
0x0041426f:	nop
0x00414270:	jmp 0x00000000
Unknown Node: Unknown Node	
0x00409933:	orl (%ecx), $0x0<UINT8>
0x00409936:	subl %eax, $0x1000<UINT32>
0x0040993b:	cmpl %eax, $0x1000<UINT32>
0x00409940:	ja 0x0040992d
Unknown Node: Unknown Node	
Unknown Node: Unknown Node	
